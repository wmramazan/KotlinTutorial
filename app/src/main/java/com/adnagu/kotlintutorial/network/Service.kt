package com.adnagu.kotlintutorial.network

import com.adnagu.kotlintutorial.model.Notification
import com.adnagu.kotlintutorial.model.Post
import com.adnagu.kotlintutorial.util.Constants
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.JsonClass
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

/**
 * Created on 8/16/2019
 * @author wmramazan
 */
interface KotlinTutorialService {
    @GET("notification")
    fun getNotificationsAsync(): Deferred<ServiceResponse<List<Notification>>>

    @GET("post")
    fun getPostsAsync(): Deferred<ServiceResponse<List<Post>>>
}

object KotlinTutorialNetwork {

    private val client = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor())
        .build()
    private val retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    val service: KotlinTutorialService = retrofit.create(KotlinTutorialService::class.java)

}