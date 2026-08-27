package com.sigeschool.services.billing

/**
 * Servicio para la firma digital de facturas electrónicas siguiendo el estándar XAdES-EPES
 * requerido por la DIAN (Colombia).
 */
interface DianSignatureService {
    /**
     * Firma un XML de factura electrónica (UBL 2.1).
     *
     * @param xmlContent El contenido XML de la factura a firmar.
     * @param certificateData Los bytes del certificado digital (PKCS12).
     * @param password La contraseña del certificado.
     * @return El XML firmado con el elemento ds:Signature y extensiones XAdES.
     */
    suspend fun signInvoice(
        xmlContent: String,
        certificateData: ByteArray,
        password: String
    ): String
}
