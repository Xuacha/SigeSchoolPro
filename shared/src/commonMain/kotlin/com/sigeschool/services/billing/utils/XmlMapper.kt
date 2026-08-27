package com.sigeschool.services.billing.utils

import com.sigeschool.domain.model.billing.Invoice
import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.TimeZone

class XmlMapper {

    fun toUbl21Xml(invoice: Invoice): String {
        val localDateTime = invoice.date.toLocalDateTime(TimeZone.currentSystemDefault())
        val dateStr = localDateTime.date.toString()
        val timeStr = "${localDateTime.hour.toString().padStart(2, '0')}:${localDateTime.minute.toString().padStart(2, '0')}:${localDateTime.second.toString().padStart(2, '0')}-05:00"

        return """
            <?xml version="1.0" encoding="UTF-8" standalone="no"?>
            <Invoice xmlns="urn:oasis:names:specification:ubl:schema:xsd:Invoice-2"
                     xmlns:cac="urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2"
                     xmlns:cbc="urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2"
                     xmlns:ds="http://www.w3.org/2000/09/xmldsig#"
                     xmlns:ext="urn:oasis:names:specification:ubl:schema:xsd:CommonExtensionComponents-2">
                <ext:UBLExtensions>
                    <ext:UBLExtension>
                        <ext:ExtensionContent>
                            <!-- Firma Digital Placeholder -->
                        </ext:ExtensionContent>
                    </ext:UBLExtension>
                </ext:UBLExtensions>
                <cbc:UBLVersionID>UBL 2.1</cbc:UBLVersionID>
                <cbc:CustomizationID>10</cbc:CustomizationID>
                <cbc:ProfileID>DIAN 2.1</cbc:ProfileID>
                <cbc:ID>${invoice.number}</cbc:ID>
                <cbc:UUID schemeID="2">${generateCufePlaceholder(invoice)}</cbc:UUID>
                <cbc:IssueDate>$dateStr</cbc:IssueDate>
                <cbc:IssueTime>$timeStr</cbc:IssueTime>
                <cbc:InvoiceTypeCode>01</cbc:InvoiceTypeCode>
                <cbc:Note>Factura generada por SigeSchool Pro - Res. 000042</cbc:Note>
                <cbc:DocumentCurrencyCode>COP</cbc:DocumentCurrencyCode>
                
                <cac:AccountingSupplierParty>
                    <cac:Party>
                        <cac:PartyTaxScheme>
                            <cbc:RegistrationName>INSTITUCIÓN EDUCATIVA SIGESCHOOL</cbc:RegistrationName>
                            <cbc:CompanyID schemeID="1">900123456</cbc:CompanyID>
                            <cac:TaxScheme>
                                <cbc:ID>01</cbc:ID>
                                <cbc:Name>IVA</cbc:Name>
                            </cac:TaxScheme>
                        </cac:PartyTaxScheme>
                    </cac:Party>
                </cac:AccountingSupplierParty>

                <cac:AccountingCustomerParty>
                    <cac:Party>
                        <cac:PartyTaxScheme>
                            <cbc:RegistrationName>${invoice.parentName}</cbc:RegistrationName>
                            <cbc:CompanyID schemeID="3">${invoice.parentId}</cbc:CompanyID>
                            <cac:TaxScheme>
                                <cbc:ID>ZY</cbc:ID>
                                <cbc:Name>No causa</cbc:Name>
                            </cac:TaxScheme>
                        </cac:PartyTaxScheme>
                    </cac:Party>
                </cac:AccountingCustomerParty>

                <cac:LegalMonetaryTotal>
                    <cbc:LineExtensionAmount currencyID="COP">${invoice.totalAmount}</cbc:LineExtensionAmount>
                    <cbc:TaxExclusiveAmount currencyID="COP">0.00</cbc:TaxExclusiveAmount>
                    <cbc:TaxInclusiveAmount currencyID="COP">${invoice.totalAmount}</cbc:TaxInclusiveAmount>
                    <cbc:PayableAmount currencyID="COP">${invoice.totalAmount}</cbc:PayableAmount>
                </cac:LegalMonetaryTotal>

                ${generateInvoiceLines(invoice)}

            </Invoice>
        """.trimIndent()
    }

    private fun generateInvoiceLines(invoice: Invoice): String {
        return invoice.items.joinToString("\n") { item ->
            """
            <cac:InvoiceLine>
                <cbc:ID>${item.id}</cbc:ID>
                <cbc:InvoicedQuantity unitCode="94">${item.quantity}</cbc:InvoicedQuantity>
                <cbc:LineExtensionAmount currencyID="COP">${item.total}</cbc:LineExtensionAmount>
                <cac:Item>
                    <cbc:Description>${item.description}</cbc:Description>
                </cac:Item>
                <cac:Price>
                    <cbc:PriceAmount currencyID="COP">${item.unitPrice}</cbc:PriceAmount>
                </cac:Price>
            </cac:InvoiceLine>
            """
        }
    }

    private fun generateCufePlaceholder(invoice: Invoice): String {
        return "CUFE-SHA384-PLACEHOLDER-${invoice.number}-${invoice.totalAmount}"
    }
}
