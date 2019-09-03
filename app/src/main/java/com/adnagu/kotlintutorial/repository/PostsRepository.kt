package com.adnagu.kotlintutorial.repository

import androidx.lifecycle.LiveData
import com.adnagu.kotlintutorial.database.PostDao
import com.adnagu.kotlintutorial.model.Post
import com.adnagu.kotlintutorial.network.KotlinTutorialNetwork
import com.adnagu.kotlintutorial.network.NetworkException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

/**
 * Created on 8/16/2019
 * @author wmramazan
 */
class PostsRepository(private val dao: PostDao) {

    val posts: LiveData<List<Post>> = dao.getAll()

    suspend fun refreshPosts() {
        withContext(Dispatchers.IO) {
            Timber.d("Refreshing posts..")

            val response = KotlinTutorialNetwork.service.getPostsAsync().await()

            if (response.success) {
                response.data?.apply {
                    dao.deleteAll()
                    dao.insertAll(this)
                }
            } else {
                throw NetworkException(response.message)
            }
        }
    }

}