package com.adnagu.kotlintutorial.util.base

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created on 8/16/2019
 * @author wmramazan
 */
interface DateModel {

    val date: Long

    val dateString: String
        get() {
            val format = SimpleDateFormat("MM-dd-yyyy HH:mm", Locale.getDefault())
            return format.format(date)
        }

    val dateObject: Date
        get() = Date(date)

}