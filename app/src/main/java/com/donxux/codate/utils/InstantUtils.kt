package com.donxux.codate.utils

import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.Locale

fun ZonedDateTime.toTimeForChat(other: ZonedDateTime): String {
    return if (year != other.year) {
        this.toLocalDate().format(
            DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)
                .withLocale(Locale.getDefault())
        )
    } else if (month == other.month && dayOfMonth + 1 == other.dayOfMonth) {
        "어제"
    } else if (month != other.month || dayOfMonth + 1 < other.dayOfMonth) {
        this.toLocalDate().format(
            DateTimeFormatter.ofPattern("MMM dd")
        )
    } else {
        this.toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm"))
    }
}
