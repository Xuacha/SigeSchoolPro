package com.sigeschool.domain.usecase.billing

import com.sigeschool.domain.model.billing.BillingStatus
import com.sigeschool.domain.model.billing.InvoiceStatus
import com.sigeschool.domain.model.billing.Invoice
import com.sigeschool.domain.model.billing.PaymentRecord
import kotlinx.datetime.Clock

class CalculateInvoiceLogicUseCase {
    
    fun calculateNewStatus(invoice: Invoice, newPayments: List<PaymentRecord>): BillingStatus {
        val totalPaid = newPayments.sumOf { it.amount }
        val now = Clock.System.now()
        
        return when {
            totalPaid >= invoice.totalAmount -> BillingStatus.PAID
            totalPaid > 0 -> BillingStatus.PARTIAL
            now > invoice.dueDate -> BillingStatus.OVERDUE
            else -> BillingStatus.PENDING
        }
    }
    
    fun calculateOverdueInterest(invoice: Invoice, dailyRate: Double = 0.0005): Double {
        val now = Clock.System.now()
        // Comparar con el estado de pago, no con el estado de la DIAN
        if (now <= invoice.dueDate) return 0.0
        
        val daysOverdue = (now - invoice.dueDate).inWholeDays
        return invoice.balance * dailyRate * daysOverdue
    }
}
