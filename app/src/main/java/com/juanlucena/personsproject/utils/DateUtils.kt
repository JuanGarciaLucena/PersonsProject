package com.juanlucena.personsproject.utils

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class DateUtils {

    companion object {
        fun formatDate(dateString: String): String {
            val instant = Instant.parse(dateString)
            val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
                .withZone(ZoneId.systemDefault())
            return formatter.format(instant)
        }
    }
}