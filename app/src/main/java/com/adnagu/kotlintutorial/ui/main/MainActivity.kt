package com.adnagu.kotlintutorial.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.adnagu.kotlintutorial.R
import com.adnagu.kotlintutorial.databinding.ActivityMainBinding
import com.adnagu.kotlintutorial.model.Notification
import com.adnagu.kotlintutorial.model.Post
import com.adnagu.kotlintutorial.ui.adapter.PagerAdapter
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var pagerAdapter: PagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this, MainViewModel.Factory(application))
            .get(MainViewModel::class.java)

        observe()

        binding.apply {
            lifecycleOwner = this@MainActivity
            viewModel = this@MainActivity.viewModel

            pagerAdapter = PagerAdapter(
                this@MainActivity,
                supportFragmentManager
            )

            viewPager.adapter = pagerAdapter
            tabs.setupWithViewPager(viewPager)
        }
    }

    private fun observe() {
        viewModel.posts.observe(this, Observer<List<Post>> { posts ->
            posts?.apply {
                pagerAdapter.setPosts(posts)
                viewModel.onSuccessfulRefresh()
            }
        })

        viewModel.notifications.observe(this, Observer<List<Notification>> { notifications ->
            notifications?.apply {
                pagerAdapter.setNotifications(notifications)
                viewModel.onSuccessfulRefresh()
            }
        })

        viewModel.eventNetworkError.observe(this, Observer<String> { error ->
            error?.apply {
                onNetworkError()
            }
        })
    }

    private fun onNetworkError() {
        if (!viewModel.isNetworkErrorShown.value!!) {
            Snackbar.make(binding.root, viewModel.eventNetworkError.value!!, Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.try_again) {
                    viewModel.refreshDataFromRepositories()
                }.show()
            viewModel.onNetworkErrorShown()
        }
    }
}