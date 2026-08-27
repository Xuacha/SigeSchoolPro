package com.sigeschool.util

import com.sigeschool.domain.model.FinancialStatement

object FinancialStatementGenerator {
    fun financialToText(statement: FinancialStatement): String {
        val sb = StringBuilder()
        sb.append("INSTITUCIÓN: ${statement.institutionName}\n")
        sb.append("TÍTULO: ${statement.title}\n")
        sb.append("PERIODO: ${statement.period}\n\n")
        
        statement.sections.forEach { section ->
            sb.append("${section.title}\n")
            section.items.forEach { item ->
                sb.append("  ${item.code} ${item.name}: $${item.balance}\n")
            }
            sb.append("SUBTOTAL: $${section.subtotal}\n\n")
        }
        
        sb.append("${statement.totalLabel}: $${statement.totalValue}\n")
        return sb.toString()
    }
}
