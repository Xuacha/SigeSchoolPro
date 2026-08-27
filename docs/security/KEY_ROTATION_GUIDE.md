# Guía de Rotación de Llaves para Administradores

## 1. Introducción
La rotación de llaves es un procedimiento crítico para mantener el cumplimiento de la norma ISO 27001 y mitigar el impacto de una posible exfiltración de llaves. SigeSchool Pro automatiza gran parte de este proceso a través del `KeyRotationManager`.

## 2. Política de Rotación
- **Frecuencia:** Se recomienda realizar una rotación de llaves cada **90 días** o inmediatamente si se sospecha de un compromiso del dispositivo.
- **Activación:** La rotación se activa mediante una actualización de la configuración remota (Firebase Remote Config o Supabase Config) que incrementa el `CURRENT_KEY_VERSION`.

## 3. Procedimiento Técnico (Interno)
1. **Detección:** Al iniciar la app, se compara la versión de la llave almacenada localmente con la versión requerida.
2. **Generación:** Se invoca `keyRotationManager.rotateKey()`, lo que genera un nuevo alias `SigeSchoolMasterKey_vN+1` en el TEE.
3. **Migración (Rekey):**
   - Se abre la base de datos con la llave `vN`.
   - Se ejecuta el comando `PRAGMA rekey` para aplicar la nueva llave `vN+1`.
4. **Limpieza:** Una vez confirmada la integridad de la nueva base de datos, se marca la llave `vN` para su posterior eliminación segura.

## 4. Manejo de Errores
- **Fallo de Energía:** Si el proceso se interrumpe, el sistema mantiene la base de datos original intacta (transaccionalidad atómica de SQLCipher).
- **Rollback:** Si la nueva clave falla, el sistema vuelve a intentar con la clave `vN` y registra un log de error crítico.

## 5. Responsabilidades
- **Staff Engineer:** Supervisar la implementación de la lógica de re-encripción.
- **Admin de TI:** Coordinar la ventana de mantenimiento para la rotación (generalmente transparente para el usuario final).
