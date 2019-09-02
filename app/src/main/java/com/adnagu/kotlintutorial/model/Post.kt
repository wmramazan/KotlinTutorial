package com.adnagu.kotlintutorial.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.adnagu.kotlintutorial.util.base.DateModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created on 8/16/2019
 * @author wmramazan
 */
@Entity(tableName = "post")
@JsonClass(generateAdapter = true)
data class Post(
    @PrimaryKey
    val id: Int,
    @Embedded
    val user: User,
    val post: String,
    override val date: Long
) : DateModel

@JsonClass(generateAdapter = true)
data class User(
    val name: String,
    val nickname: String,
    @ColumnInfo(name = "profile_image")
    @Json(name = "profile_image")
    val profileImageUrl: String
)