package com.sigeschool.domain.model

import kotlinx.serialization.Serializable

import kotlin.math.abs

@Serializable
enum class EntryType {
    FACTURA_VENTA,
    RECIBO_CAJA,
    COMPROBANTE_EGRESO,
    NOTA_CONTABLE,
    NOMINA,
    AJUSTE_IA,
    CIERRE_MENSUAL
}

@Serializable
data class AccountingEntry(
    val id: String = "",
    val date: String = "", // ISO 8601
    val description: String = "",
    val institutionId: String = "",
    val type: EntryType = EntryType.NOTA_CONTABLE,
    val centerId: String? = null, // Centro de Costos
    val entries: List<EntryDetail> = emptyList(),
    val totalDebit: Double = 0.0,
    val totalCredit: Double = 0.0,
    val isElectronicInvoiced: Boolean = false,
    val metadata: Map<String, String> = emptyMap()
) {
    val isBalanced: Boolean get() = abs(totalDebit - totalCredit) < 0.01
    
    // Método para autocalcular totales si se requiere
    fun calculateTotals(): AccountingEntry {
        val debit = entries.sumOf { it.debit }
        val credit = entries.sumOf { it.credit }
        return this.copy(totalDebit = debit, totalCredit = credit)
    }
}

@Serializable
data class EntryDetail(
    val accountCode: String = "",
    val accountName: String = "",
    val debit: Double = 0.0,
    val credit: Double = 0.0,
    val thirdPartyId: String? = null, // NIT/Cédula del tercero
    val thirdPartyName: String? = null
)
