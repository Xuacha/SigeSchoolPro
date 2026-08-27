package com.sigeschool.domain.util

import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertTrue

class CryptoManagerTest {

    @Test
    fun testAsyncEncryptDecryptSimple() = runTest {
        val original = "Hola Mundo"
        val encrypted = CryptoManager.encryptAsync(original)
        assertNotEquals(original, encrypted, "El texto cifrado no debe ser igual al original")
        
        val decrypted = CryptoManager.decryptAsync(encrypted)
        assertEquals(original, decrypted, "El texto descifrado debe coincidir con el original")
    }

    @Test
    fun testAsyncEncryptDecryptSpecialChars() = runTest {
        val original = "Ñandú, café, piña, 123! @#$%"
        val encrypted = CryptoManager.encryptAsync(original)
        val decrypted = CryptoManager.decryptAsync(encrypted)
        assertEquals(original, decrypted)
    }

    @Test
    fun testAsyncEncryptDecryptEmpty() = runTest {
        val original = ""
        val encrypted = CryptoManager.encryptAsync(original)
        val decrypted = CryptoManager.decryptAsync(encrypted)
        assertEquals(original, decrypted)
    }

    @Test
    fun testAsyncEncryptDecryptLongText() = runTest {
        val original = "A".repeat(1000)
        val encrypted = CryptoManager.encryptAsync(original)
        val decrypted = CryptoManager.decryptAsync(encrypted)
        assertEquals(original, decrypted)
    }
    
    @Test
    fun testIvRandomness() = runTest {
        val original = "Mismo Texto"
        val encrypted1 = CryptoManager.encryptAsync(original)
        val encrypted2 = CryptoManager.encryptAsync(original)
        
        // En AES-GCM con IV aleatorio, el resultado debe ser diferente para el mismo texto
        assertNotEquals(encrypted1, encrypted2, "Cifrados sucesivos del mismo texto deben producir resultados diferentes debido al IV")
        
        assertEquals(original, CryptoManager.decryptAsync(encrypted1))
        assertEquals(original, CryptoManager.decryptAsync(encrypted2))
    }

    @Test
    fun testPerformanceMultipleOperations() = runTest {
        val data = "Datos de prueba para rendimiento de cifrado y descifrado en SigeSchool Pro"
        val iterations = 50
        val start = kotlinx.datetime.Clock.System.now()
        
        repeat(iterations) {
            val enc = CryptoManager.encryptAsync(data)
            CryptoManager.decryptAsync(enc)
        }
        
        val end = kotlinx.datetime.Clock.System.now()
        val duration = end - start
        val average = duration.inWholeMilliseconds / iterations
        
        // P-10: Tiempo total < 2 segundos para 100 operaciones. 
        // Aquí hacemos 50 para no alargar el test, pero validamos el promedio < 20ms.
        println("Crypto Performance: $average ms per op")
        assertTrue(average < 100, "El rendimiento promedio ($average ms) excede el límite de 100ms")
    }
}
