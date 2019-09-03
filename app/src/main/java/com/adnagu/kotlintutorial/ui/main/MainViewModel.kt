package com.adnagu.kotlintutorial.ui.main

import android.app.Application
import androidx.lifecycle.*
import com.adnagu.kotlintutorial.R
import com.adnagu.kotlintutorial.database.getDatabase
import com.adnagu.kotlintutorial.network.NetworkException
import com.adnagu.kotlintutorial.repository.NotificationsRepository
import com.adnagu.kotlintutorial.repository.PostsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

/**
 * Created on 8/22/2019
 * @author wmramazan
 */
class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val database = getDatabase(application)

    private val postsRepository = PostsRepository(database.postDao)
    private val notificationsRepository = NotificationsRepository(database.notificationDao)

    val posts = postsRepository.posts
    val notifications = notificationsRepository.notifications

    private val defaultError = application.getString(R.string.network_error)

    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private var _eventNetworkError = MutableLiveData<String>(null)
    val eventNetworkError: LiveData<String>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData(false)
    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    private var _isSuccessfulRefresh = MutableLiveData(false)
    val isSuccessfulRefresh: LiveData<Boolean>
        get() = _isSuccessfulRefresh

    init {
        refreshDataFromRepositories()
    }

    fun refreshDataFromRepositories() {
        viewModelScope.launch {
            try {
                _eventNetworkError.value = null
                _isNetworkErrorShown.value = false
                _isSuccessfulRefresh.value = false
                postsRepository.refreshPosts()
                notificationsRepository.refreshNotifications()
            } catch (e: Exception) {
                e.printStackTrace()

                _eventNetworkError.value = if (e is NetworkException) e.message
                    else defaultError
            }
        }
    }

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }

    fun onSuccessfulRefresh() {
        _isSuccessfulRefresh.value = true
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    class Factory(private val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return MainViewModel(application) as T
            }
            throw IllegalArgumentException("Unable to construct ViewModel.")
        }
    }

}