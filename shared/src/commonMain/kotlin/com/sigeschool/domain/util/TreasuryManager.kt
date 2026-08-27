package com.sigeschool.domain.util

import com.sigeschool.domain.model.*
import kotlinx.datetime.*

object TreasuryManager {

    fun createSummary(
        date: String,
        payments: List<FeePayment>
    ): CashClosingSummary {
        val totalByMethod = payments.groupBy { it.metodoPago }
            .mapValues { entry -> entry.value.sumOf { it.monto } }
        
        val totalGeneral = payments.sumOf { it.monto }
        
        return CashClosingSummary(
            date = date,
            payments = payments,
            totalByMethod = totalByMethod,
            totalGeneral = totalGeneral
        )
    }

    fun generateClosing(
        institutionId: String,
        summary: CashClosingSummary,
        userId: String,
        observations: String = ""
    ): CashClosing {
        val now = Clock.System.now()
        val timestamp = now.toEpochMilliseconds()
        
        return CashClosing(
            id = "CLOSE_${institutionId}_${summary.date.replace("/", "")}",
            date = summary.date,
            institutionId = institutionId,
            totalCash = summary.totalByMethod["EFECTIVO"] ?: 0.0,
            totalTransfer = summary.totalByMethod["TRANSFERENCIA"] ?: 0.0,
            totalOther = summary.totalGeneral - (summary.totalByMethod["EFECTIVO"] ?: 0.0) - (summary.totalByMethod["TRANSFERENCIA"] ?: 0.0),
            totalGeneral = summary.totalGeneral,
            closedBy = userId,
            closingTimestamp = timestamp,
            observations = observations
        )
    }

    /**
     * Genera el asiento contable automático del cierre de caja
     * Débito: Caja (1105) o Bancos (1110)
     * Crédito: Ingresos Recibidos por Anticipado (2705) o Pensiones (4160)
     * Para simplificar en este ejemplo: 110505 (Caja General) vs 416005 (Enseñanza Preescolar/Primaria/Secundaria)
     */
    fun generateAccountingEntry(closing: CashClosing, institutionId: String): AccountingEntry {
        val details = mutableListOf<EntryDetail>()
        
        if (closing.totalCash > 0) {
            details.add(EntryDetail(accountCode = "110505", accountName = "Caja General", debit = closing.totalCash, credit = 0.0))
        }
        if (closing.totalTransfer > 0) {
            details.add(EntryDetail(accountCode = "111005", accountName = "Bancos Moneda Nacional", debit = closing.totalTransfer, credit = 0.0))
        }
        if (closing.totalOther > 0) {
            details.add(EntryDetail(accountCode = "110510", accountName = "Cajas Menores", debit = closing.totalOther, credit = 0.0))
        }
        
        details.add(EntryDetail(
            accountCode = "416005", 
            accountName = "Servicios de Educación", 
            debit = 0.0, 
            credit = closing.totalGeneral
        ))

        return AccountingEntry(
            id = "ACC_${closing.id}",
            date = closing.date,
            description = "Cierre de Caja Diario - ${closing.date}",
            institutionId = institutionId,
            entries = details,
            totalDebit = closing.totalGeneral,
            totalCredit = closing.totalGeneral
        )
    }
}
