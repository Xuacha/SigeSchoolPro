package com.sigeschool.services.vision

import org.bytedeco.javacpp.Pointer
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicLong
import java.util.logging.Logger

/**
 * Gestor de recursos nativos para asegurar la liberación de memoria off-heap de JavaCPP.
 * Implementa un sistema de rastreo para prevenir Memory Leaks y SIGSEGV.
 */
class NativeResourceManager : AutoCloseable {
    private val logger = Logger.getLogger("NativeResourceManager")
    private val activeResources = ConcurrentHashMap<Long, ManagedResource>()
    private val counter = AtomicLong(0)

    /**
     * Registra un puntero nativo para seguimiento.
     */
    fun <T : Pointer> register(pointer: T, name: String = "Unknown"): ManagedResource {
        val id = counter.incrementAndGet()
        val managed = ManagedResource(id, pointer, name)
        activeResources[id] = managed
        logger.fine("Resource registered: $name (ID: $id, Address: ${pointer.address()})")
        return managed
    }

    /**
     * Ejecuta un bloque de código con un recurso y asegura su liberación.
     */
    inline fun <T : Pointer, R> use(pointer: T, name: String = "Resource", block: (T) -> R): R {
        val managed = register(pointer, name)
        return try {
            block(pointer)
        } finally {
            release(managed.id)
        }
    }

    fun release(id: Long) {
        activeResources.remove(id)?.let { managed ->
            managed.close()
            logger.fine("Resource released: ${managed.name} (ID: $id)")
        }
    }

    override fun close() {
        val count = activeResources.size
        if (count > 0) {
            logger.warning("Closing ResourceManager with $count active resources. Forced cleanup initiated.")
            activeResources.keys().toList().forEach { release(it) }
        }
    }

    inner class ManagedResource(
        val id: Long,
        private val pointer: Pointer,
        val name: String
    ) : AutoCloseable {
        override fun close() {
            if (!pointer.isNull) {
                pointer.deallocate()
            }
        }
    }
}
