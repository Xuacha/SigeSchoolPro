package com.sigeschool.domain.service

import kotlin.random.Random

class PasswordGenerator {
    private val upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    private val lower = "abcdefghijklmnopqrstuvwxyz"
    private val digits = "0123456789"
    private val symbols = "!@#$%^&*"
    private val allChars = upper + lower + digits + symbols

    fun generate(length: Int = 10): String {
        val random = Random.Default
        val password = StringBuilder()
        
        // Ensure at least one of each type
        password.append(upper[random.nextInt(upper.length)])
        password.append(lower[random.nextInt(lower.length)])
        password.append(digits[random.nextInt(digits.length)])
        password.append(symbols[random.nextInt(symbols.length)])
        
        for (i in 4 until length) {
            password.append(allChars[random.nextInt(allChars.length)])
        }
        
        return password.toString().toCharArray().apply { shuffle() }.concatToString()
    }
}
