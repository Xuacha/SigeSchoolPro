package com.sigeschool.domain.usecase.billing

import com.sigeschool.domain.model.billing.*
import com.sigeschool.domain.repository.billing.BillingRepository
import kotlinx.datetime.Clock

class ProcessPaymentUseCase(private val repository: BillingRepository) {
    suspend operator fun invoke(
        invoice: Invoice,
        paymentMethod: PaymentMethod,
        amount: Double,
        registrarId: String
    ): Result<PaymentRecord> {
        return try {
            val payment = PaymentRecord(
                id = "PAY_${Clock.System.now().toEpochMilliseconds()}",
                invoiceId = invoice.id,
                amount = amount,
                date = Clock.System.now(),
                paymentMethod = paymentMethod,
                institutionId = invoice.institutionId,
                registrarId = registrarId
            )
            repository.savePayment(payment)
            Result.success(payment)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
