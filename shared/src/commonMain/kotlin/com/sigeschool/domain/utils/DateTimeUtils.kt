package com.sigeschool.domain.utils

import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.ExperimentalTime

object DateTimeUtils {
    @OptIn(ExperimentalTime::class)
    fun getCurrentDateString(): String {
        val now = kotlinx.datetime.Clock.System.now()
        val zone = TimeZone.currentSystemDefault()
        return now.toLocalDateTime(zone).date.toString()
    }
}
