package com.example.dayscheduler.data.db.converter

import androidx.room.TypeConverter
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

object ZonedDateTimeConverter {
    private val formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME

    @JvmStatic
    @TypeConverter
    fun toTimestamp(date: ZonedDateTime?): String? = date?.format(formatter)

    @JvmStatic
    @TypeConverter
    fun fromTimestamp(value: String?): ZonedDateTime? {
        return value?.let {
            ZonedDateTime.from(formatter.parse(it))
        }
    }
}