package com.adnagu.kotlintutorial.network

import com.squareup.moshi.JsonClass

/**
 * Created on 8/16/2019
 * @author wmramazan
 */
@JsonClass(generateAdapter = true)
data class ServiceResponse<T : Any>(
    val data: T?,
    val success: Boolean?,
    val message: String?
)