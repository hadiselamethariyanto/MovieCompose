package banyuwangi.digital.moviecompose.utils

import java.text.SimpleDateFormat
import java.util.*

object Utils {

    fun dateStringToYear(date: String): String {
        val parser = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val formatter = SimpleDateFormat("yyyy", Locale.getDefault())
        return try {
            return formatter.format(parser.parse(date) ?: Date())
        } catch (e: Exception) {
            "2022"
        }
    }
}