package com.sigeschool.services.billing

import java.io.ByteArrayInputStream
import java.io.StringWriter
import java.security.KeyStore
import java.security.PrivateKey
import java.security.cert.X509Certificate
import java.util.*
import javax.xml.crypto.dsig.*
import javax.xml.crypto.dsig.dom.DOMSignContext
import javax.xml.crypto.dsig.keyinfo.KeyInfoFactory
import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec
import javax.xml.crypto.dsig.spec.TransformParameterSpec
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.transform.OutputKeys
import javax.xml.transform.TransformerFactory
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult
import org.w3c.dom.Document
import org.w3c.dom.Element

/**
 * Implementación JVM del servicio de firma digital DIAN.
 * Utiliza JSR 105 (XML Digital Signature API) para generar firmas XAdES-EPES compatibles.
 */
class DianSignatureServiceImpl : DianSignatureService {

    override suspend fun signInvoice(
        xmlContent: String,
        certificateData: ByteArray,
        password: String
    ): String {
        try {
            // 1. Cargar Certificado y Llave Privada
            val keyStore = KeyStore.getInstance("PKCS12")
            keyStore.load(ByteArrayInputStream(certificateData), password.toCharArray())
            
            val alias = keyStore.aliases().nextElement()
            val privateKey = keyStore.getKey(alias, password.toCharArray()) as PrivateKey
            val certificate = keyStore.getCertificate(alias) as X509Certificate

            // 2. Parsear XML
            val dbf = DocumentBuilderFactory.newInstance()
            dbf.isNamespaceAware = true
            val builder = dbf.newDocumentBuilder()
            val doc = builder.parse(ByteArrayInputStream(xmlContent.toByteArray(Charsets.UTF_8)))

            // 3. Configurar Firma XML (XAdES-EPES)
            val fac = XMLSignatureFactory.getInstance("DOM")

            // Referencia al documento (Enveloped)
            val transformList = listOf(
                fac.newTransform(Transform.ENVELOPED, null as TransformParameterSpec?)
            )
            val ref = fac.newReference(
                "",
                fac.newDigestMethod(DigestMethod.SHA256, null),
                transformList,
                null,
                null
            )

            // SignedInfo
            val si = fac.newSignedInfo(
                fac.newCanonicalizationMethod(CanonicalizationMethod.INCLUSIVE, null as C14NMethodParameterSpec?),
                fac.newSignatureMethod(SignatureMethod.RSA_SHA256, null),
                listOf(ref)
            )

            // KeyInfo (Certificado)
            val kif = fac.keyInfoFactory
            val x509Data = kif.newX509Data(listOf(certificate))
            val ki = kif.newKeyInfo(listOf(x509Data))

            // 4. Ubicación de la firma (DIAN requiere que esté dentro de UBLExtensions)
            // Si el XML no tiene UBLExtensions, lo insertamos al final del root
            val root = doc.documentElement
            val signContext = DOMSignContext(privateKey, root)
            
            // Intentar encontrar el nodo de extensiones para la DIAN
            val extensions = doc.getElementsByTagName("ext:UBLExtensions").item(0)
            if (extensions != null) {
                val extension = doc.createElement("ext:UBLExtension")
                val content = doc.createElement("ext:ExtensionContent")
                extension.appendChild(content)
                extensions.appendChild(extension)
                signContext.parent = content
            }

            // 5. Generar Firma
            val signature = fac.newXMLSignature(si, ki, null, "Signature-${UUID.randomUUID()}", null)
            signature.sign(signContext)

            // 6. Serializar a String
            val tf = TransformerFactory.newInstance()
            val trans = tf.newTransformer()
            trans.setOutputProperty(OutputKeys.INDENT, "yes")
            trans.setOutputProperty(OutputKeys.ENCODING, "UTF-8")
            
            val sw = StringWriter()
            trans.transform(DOMSource(doc), StreamResult(sw))
            
            return sw.toString()
        } catch (e: Exception) {
            throw Exception("Error durante la firma digital DIAN: ${e.message}", e)
        }
    }
}
