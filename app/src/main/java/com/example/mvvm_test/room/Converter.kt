package com.example.mvvm_test.room

import android.annotation.SuppressLint
import androidx.room.TypeConverter
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

class Converter {
    @TypeConverter
    fun fromDateString(value: String?): LocalDate? {
        return DateUtils().stringToDate(value!!)
    }

    @TypeConverter
    fun dateToString(date: LocalDate?): String? {
        return DateUtils().dateToString(date!!)
    }
}

class DateUtils {
    @SuppressLint("NewApi")
    fun stringToDate(dateString: String): LocalDate? {
        return try {
            LocalDate.parse(dateString)
        }catch (exception: DateTimeParseException){
            null
        }
    }

    @SuppressLint("NewApi")
    fun dateToString(date: LocalDate): String? {
        return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
    }
}