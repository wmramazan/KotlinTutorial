package com.adnagu.kotlintutorial.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.adnagu.kotlintutorial.util.base.DateModel
import com.squareup.moshi.JsonClass

/**
 * Created on 8/16/2019
 * @author wmramazan
 */
@Entity(tableName = "notification")
@JsonClass(generateAdapter = true)
data class Notification(
    @PrimaryKey
    val id: Int,
    val title: String,
    val description: String,
    override val date: Long
) : DateModel