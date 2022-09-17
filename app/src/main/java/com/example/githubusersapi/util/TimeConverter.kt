package com.example.githubusersapi.util

import java.text.SimpleDateFormat
import java.util.*

object TimeConverter {
    @JvmStatic
    fun timestampToDate(date:Long) : String {
        val simpleDateFormat = SimpleDateFormat("MMM, dd yyyy HH:mm")
        return simpleDateFormat.format(Date(date.times(1000)))
    }
}