# Trazabilidad Financiera e Integridad de Facturación

## 1. Contexto
Para garantizar el cumplimiento de las normativas contables y de la DIAN, se requiere una trazabilidad absoluta entre los pagos registrados y las facturas electrónicas emitidas.

## 2. Solución Implementada
Se han realizado las siguientes modificaciones en la capa de datos y dominio:

- **InvoiceEntity**: Se añadió el campo `pagoId` como clave foránea (conceptual en Room con índices de búsqueda) para vincular cada factura con su transacción de origen.
- **InvoiceStatus**: Se implementó una máquina de estados para las facturas (`DRAFT`, `SENT`, `PAID`, `CANCELLED`, `STANDBY`).
- **FinancialMapper**: Se centralizó la lógica de conversión para asegurar que el `pagoId` se preserve en todo el ciclo de vida del objeto.

## 3. Verificación de Integridad
Se validó mediante pruebas unitarias que:
1. No se pueden crear facturas sin un `pagoId` válido.
2. Un pago puede ser consultado desde la factura y viceversa.
3. El estado de la factura se actualiza automáticamente al confirmar el pago en el ledger auditado.

## 4. Auditoría
Esta implementación cierra el Hallazgo A-06 de la auditoría v2.0.
