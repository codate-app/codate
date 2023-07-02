package com.donxux.codate.data.db

import androidx.room.TypeConverter
import java.time.Instant
import java.time.ZonedDateTime
import java.util.TimeZone

object Converters {
//    @TypeConverter
//    fun fromTimestamp(value: Long?): Instant? {
//        return value?.let { Instant.ofEpochMilli(it) }
//    }

    @TypeConverter
    fun instantToTimestamp(instant: Instant?): Long? {
        return instant?.toEpochMilli()
    }

    @TypeConverter
    fun fromTimestamp(value: Long?): ZonedDateTime? {
        return value?.let {
            return ZonedDateTime.ofInstant(
                Instant.ofEpochSecond(value),
                TimeZone.getTimeZone("Asia/Seoul").toZoneId()
            )
        }
    }

    @TypeConverter
    fun zonedDateTimeToTimestamp(dateTime: ZonedDateTime?): Long? {
        return dateTime?.toEpochSecond()
    }
}
