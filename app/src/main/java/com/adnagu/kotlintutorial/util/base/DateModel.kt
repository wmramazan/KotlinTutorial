package com.adnagu.kotlintutorial.util.base

import android.text.format.DateUtils
import java.util.*

/**
 * Created on 8/16/2019
 * @author wmramazan
 */
interface DateModel {

    val date: Long

    val dateString: String
        get() {
            return DateUtils.getRelativeTimeSpanString(date).toString()
        }

    val dateObject: Date
        get() = Date(date)

}