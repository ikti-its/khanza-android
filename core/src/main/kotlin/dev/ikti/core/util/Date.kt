package dev.ikti.core.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun convertMillisToDate(millis: Long): String {
    val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    return formatter.format(Date(millis))
}

fun convertStringDateToMillis(date: String): Long {
    val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val convertedDate = formatter.parse(date) as Date
    return convertedDate.time
}

fun formatDateString(dateStr: String): String {
    val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val date = formatter.parse(dateStr) as Date
    val outputFormatter = SimpleDateFormat("d MMMM yyyy", Locale.getDefault())
    return outputFormatter.format(date)
}
