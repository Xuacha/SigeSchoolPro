# Mapeo de Factura a XML UBL 2.1 (DIAN Colombia)

## 1. Estándar
Se sigue el anexo técnico de la Resolución 000042 de la DIAN para el formato XML UBL 2.1.

## 2. Componentes Críticos
- **CUFE (Código Único de Factura Electrónica)**: Generado mediante SHA-384 concatenando:
  - NumFactura + Fecha + Hora + Valor + Impuestos + NIT Emisor + NIT Adquirente + Clave Técnica.
- **QR Code**: Contiene la URL de validación de la DIAN + CUFE.

## 3. Implementación del Mapeador (`XmlMapper.kt`)
Se utiliza un enfoque de plantillas controladas (Template strings o XML Serialization) para garantizar la estructura jerárquica:
- `<cac:AccountingSupplierParty>`: Datos de la institución.
- `<cac:AccountingCustomerParty>`: Datos del estudiante/acudiente.
- `<cac:TaxTotal>`: Desglose de IVA/ICA según corresponda.
- `<cac:LegalMonetaryTotal>`: Totales finales.

## 4. Modo Standby
Si no hay conexión con el proveedor tecnológico o la DIAN, el sistema genera el XML localmente, lo marca como `STANDBY` en la base de datos y lo encola para envío automático mediante `WorkManager` (Android) o un Job de fondo (Desktop).
