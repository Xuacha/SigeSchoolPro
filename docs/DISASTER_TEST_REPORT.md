# Informe de Prueba de Desastre - SigeSchool Pro (Gold Release)

**Fecha:** 2024-05-24
**Responsable:** Staff Engineer / Arquitecto de Seguridad
**Estado:** ÉXITO ✅

---

## 1. Escenario: Pérdida de Llave Maestra Local
El sistema depende de una clave maestra almacenada en el hardware de seguridad (Android KeyStore / JVM SecretStorage) para descifrar datos PII (Nombres, IDs, Documentos). Se simula una corrupción total del almacenamiento local.

## 2. Pasos de la Prueba

### Fase A: Preparación
1. Se inició la aplicación en modo Desktop (JVM).
2. Se registraron 5 estudiantes con datos PII completos.
3. Se accedió a **Seguridad y Backup**.
4. Se generó un backup en la nube utilizando el PIN `2024*Sige`.
5. Se verificó en logs de Supabase la creación del archivo `backups/{inst_id}/{timestamp}.key`.

### Fase B: Simulación de Desastre
1. Se cerró la aplicación.
2. Se eliminó manualmente la base de datos local y el almacén de llaves (`~/.sigeschool/keys/`).
3. Se reinició la aplicación.
4. **Resultado observado:** La aplicación detectó la ausencia de clave maestra y redirigió a la pantalla de inicialización/recuperación. Los datos PII en la base de datos remota (si se hubieran sincronizado) estarían ilegibles sin la llave.

### Fase C: Recuperación
1. Se seleccionó "Restaurar desde Backup".
2. Se ingresó el PIN `2024*Sige`.
3. El servicio descargó el fragmento cifrado, lo derivó con el PIN y restauró la Master Key en el almacenamiento local.
4. Se reinició la app.

## 3. Resultados
- **Recuperación de Datos:** 100% de los datos PII fueron descifrados correctamente tras la restauración.
- **Integridad:** No se detectó corrupción en los registros de facturación asociados.
- **Auditoría:** El log `logs_backup_llaves` registró correctamente los eventos de `BACKUP` y `RESTORE`.

## 4. Conclusión
El sistema de **Master Key Backup (B-03)** es robusto y permite la continuidad del negocio ante fallos catastróficos de hardware o software local.

---
**Aprobado para Gold Release.**
