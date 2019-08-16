package com.adnagu.kotlintutorial.repository

import androidx.lifecycle.LiveData
import com.adnagu.kotlintutorial.database.NotificationDao
import com.adnagu.kotlintutorial.model.Notification
import com.adnagu.kotlintutorial.network.KotlinTutorialNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

/**
 * Created on 8/16/2019
 * @author wmramazan
 */
class NotificationsRepository(private val dao: NotificationDao) {

    val notifications: LiveData<List<Notification>> = dao.getAll()

    suspend fun refreshNotifications() {
        withContext(Dispatchers.IO) {
            Timber.d("Refreshing notifications..")

            val response = KotlinTutorialNetwork.service.getNotificationsAsync().await()

            response.data?.apply {
                dao.deleteAll()
                dao.insertAll(this)
            }
        }
    }

}