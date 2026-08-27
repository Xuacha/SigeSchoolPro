package com.sigeschool.data.local.converter

import androidx.room.TypeConverter
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import com.sigeschool.domain.model.EntryDetail

import com.sigeschool.domain.model.Question

class Converters {
    @TypeConverter
    fun fromEntryDetailList(value: List<EntryDetail>): String {
        return Json.encodeToString(value)
    }

    @TypeConverter
    fun toEntryDetailList(value: String): List<EntryDetail> {
        return Json.decodeFromString(value)
    }

    @TypeConverter
    fun fromQuestionList(value: List<Question>): String {
        return Json.encodeToString(value)
    }

    @TypeConverter
    fun toQuestionList(value: String): List<Question> {
        return Json.decodeFromString(value)
    }

    @TypeConverter
    fun fromStringMap(value: Map<String, Boolean>): String {
        return Json.encodeToString(value)
    }

    @TypeConverter
    fun toStringMap(value: String): Map<String, Boolean> {
        return Json.decodeFromString(value)
    }
}
