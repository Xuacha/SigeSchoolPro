package com.sigeschool.services.export

import org.khronos.webgl.ArrayBuffer
import org.khronos.webgl.Uint8Array
import org.khronos.webgl.get

@JsModule("xlsx")
external object XLSX {
    fun write(workbook: JsAny, options: JsAny): JsAny
    val utils: XLSXUtils
}

external interface XLSXUtils : JsAny {
    fun json_to_sheet(data: JsAny): JsAny
    fun book_new(): JsAny
    fun book_append_sheet(workbook: JsAny, worksheet: JsAny, name: String)
}

@JsModule("jspdf")
external class JsPDF(orientation: String = definedExternally, unit: String = definedExternally, format: String = definedExternally) : JsAny {
    fun text(text: String, x: Double, y: Double, options: JsAny = definedExternally)
    fun output(type: String): JsAny
    fun setFontSize(size: Int)
    fun setLineWidth(width: Double)
    fun line(x1: Double, y1: Double, x2: Double, y2: Double)
    fun setFont(fontName: String, fontStyle: String = definedExternally)
    val lastAutoTable: AutoTableResult
}

external interface AutoTableResult : JsAny {
    val finalY: JsNumber
}

@JsModule("jspdf-autotable")
external object AutoTable {
    fun autoTable(doc: JsAny, options: JsAny)
}

fun JsAny.toByteArray(): ByteArray {
    val uint8Array = Uint8Array(this as ArrayBuffer)
    return ByteArray(uint8Array.length) { uint8Array[it] }
}

// Helpers para Interop de WasmJs con objetos dinámicos
fun jsobject(): JsAny = js("({ })")
fun jsarray(): JsArray<JsAny?> = js("([ ])")

fun jsSet(obj: JsAny, key: String, value: JsAny?): Unit = js("obj[key] = value")
fun jsPush(arr: JsArray<JsAny?>, value: JsAny?): Unit = js("arr.push(value)")
