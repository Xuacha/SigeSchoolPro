package com.sigeschool.domain.util

import kotlinx.browser.window
import kotlinx.coroutines.await
import org.khronos.webgl.ArrayBuffer
import org.khronos.webgl.Uint8Array
import org.khronos.webgl.get
import org.w3c.dom.set
import org.w3c.dom.get
import kotlin.js.Promise
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

@OptIn(ExperimentalEncodingApi::class)
actual object CryptoManager {
    private const val DB_NAME = "SigeSchoolCryptoDB"
    private const val STORE_NAME = "Keys"
    private const val KEY_ID = "master_key"
    private const val STORAGE_KEY_BACKUP = "sigeschool_crypto_key_backup"

    private var cachedKey: JsAny? = null

    private suspend fun getOrCreateKey(): JsAny {
        if (cachedKey != null) return cachedKey!!

        val idbKey = getKeyFromIndexedDB()
        if (idbKey != null) {
            cachedKey = idbKey
            return idbKey
        }

        val storedKeyStr = window.localStorage[STORAGE_KEY_BACKUP]
        if (storedKeyStr != null) {
            val keyData = Base64.decode(storedKeyStr)
            val buffer = Uint8Array(keyData.size)
            for (i in keyData.indices) {
                js("buffer[i] = keyData[i]")
            }
            val importPromise: Promise<JsAny> = js("window.crypto.subtle.importKey('raw', buffer.buffer, {name: 'AES-GCM'}, true, ['encrypt', 'decrypt'])")
            val key = importPromise.await()
            cachedKey = key
            saveKeyToIndexedDB(key)
            return key
        }

        val generatePromise: Promise<JsAny> = js("window.crypto.subtle.generateKey({name: 'AES-GCM', length: 256}, true, ['encrypt', 'decrypt'])")
        val newKey = generatePromise.await()
        
        saveKeyToIndexedDB(newKey)
        cachedKey = newKey
        return newKey
    }

    private suspend fun getKeyFromIndexedDB(): JsAny? {
        return try {
            val promise: Promise<JsAny?> = js("""
                new Promise((resolve, reject) => {
                    const request = indexedDB.open(com.sigeschool.domain.util.CryptoManager.DB_NAME, 1);
                    request.onupgradeneeded = (event) => {
                        const db = event.target.result;
                        db.createObjectStore(com.sigeschool.domain.util.CryptoManager.STORE_NAME);
                    };
                    request.onsuccess = (event) => {
                        const db = event.target.result;
                        const transaction = db.transaction(com.sigeschool.domain.util.CryptoManager.STORE_NAME, 'readonly');
                        const store = transaction.objectStore(com.sigeschool.domain.util.CryptoManager.STORE_NAME);
                        const getRequest = store.get(com.sigeschool.domain.util.CryptoManager.KEY_ID);
                        getRequest.onsuccess = () => resolve(getRequest.result);
                        getRequest.onerror = () => resolve(null);
                    };
                    request.onerror = () => resolve(null);
                })
            """)
            promise.await()
        } catch (e: Exception) {
            null
        }
    }

    private suspend fun saveKeyToIndexedDB(key: JsAny) {
        try {
            val promise: Promise<JsAny?> = js("""
                new Promise((resolve, reject) => {
                    const request = indexedDB.open(com.sigeschool.domain.util.CryptoManager.DB_NAME, 1);
                    request.onupgradeneeded = (event) => {
                        const db = event.target.result;
                        db.createObjectStore(com.sigeschool.domain.util.CryptoManager.STORE_NAME);
                    };
                    request.onsuccess = (event) => {
                        const db = event.target.result;
                        const transaction = db.transaction(com.sigeschool.domain.util.CryptoManager.STORE_NAME, 'readwrite');
                        const store = transaction.objectStore(com.sigeschool.domain.util.CryptoManager.STORE_NAME);
                        store.put(key, com.sigeschool.domain.util.CryptoManager.KEY_ID);
                        transaction.oncomplete = () => resolve(null);
                        transaction.onerror = () => reject(transaction.error);
                    };
                    request.onerror = () => reject(request.error);
                })
            """)
            promise.await()
        } catch (e: Exception) {
        }
    }

    actual fun encrypt(data: String): String = "ASYNC_REQUIRED:$data"
    actual fun decrypt(data: String): String = data
    
    actual fun encryptSync(data: String): String = encrypt(data)
    actual fun decryptSync(data: String): String = decrypt(data)

    actual suspend fun encryptAsync(data: String): String {
        if (data.isBlank()) return data
        return try {
            val key = getOrCreateKey()
            val iv = js("window.crypto.getRandomValues(new Uint8Array(12))") as Uint8Array
            val encoder = js("new TextEncoder()")
            val encodedData = js("encoder.encode(data)")
            
            val encryptPromise: Promise<ArrayBuffer> = js("window.crypto.subtle.encrypt({name: 'AES-GCM', iv: iv}, key, encodedData)")
            val encryptedBuffer = encryptPromise.await()
            
            val encryptedBytes = Uint8Array(encryptedBuffer)
            val combined = Uint8Array(iv.length + encryptedBytes.length)
            js("combined.set(iv); combined.set(encryptedBytes, iv.length)")
            
            val resultBytes = ByteArray(combined.length)
            for (i in 0 until combined.length) {
                resultBytes[i] = combined[i]
            }
            Base64.encode(resultBytes)
        } catch (e: Exception) {
            "ERR_ENCRYPT:$data"
        }
    }

    actual suspend fun decryptAsync(data: String): String {
        if (data.isBlank()) return data
        return try {
            val key = getOrCreateKey()
            val combinedData = Base64.decode(data)
            val combined = Uint8Array(combinedData.size)
            for (i in combinedData.indices) {
                js("combined[i] = combinedData[i]")
            }
            
            val iv = js("combined.slice(0, 12)")
            val encryptedContent = js("combined.slice(12)")
            
            val decryptPromise: Promise<ArrayBuffer> = js("window.crypto.subtle.decrypt({name: 'AES-GCM', iv: iv}, key, encryptedContent)")
            val decryptedBuffer = decryptPromise.await()
            
            val decoder = js("new TextDecoder()")
            js("decoder.decode(decryptedBuffer)") as String
        } catch (e: Exception) {
            data
        }
    }

    actual fun getMasterKeyBytes(): ByteArray {
        // En Wasm, extraer la clave requiere que sea extractable y usar exportKey (async)
        // Por consistencia con la interfaz, devolvemos vacío o lanzamos error si no es posible sincrónicamente.
        return byteArrayOf()
    }

    actual fun importMasterKey(keyBytes: ByteArray) {
        // Requiere async
    }

    actual fun encryptWithPin(data: ByteArray, pin: String): ByteArray {
        return byteArrayOf() // Implementar con SubtleCrypto (Async) si es posible
    }

    actual fun decryptWithPin(data: ByteArray, pin: String): ByteArray {
        return byteArrayOf()
    }
}
