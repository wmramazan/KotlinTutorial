package com.adnagu.kotlintutorial.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.adnagu.kotlintutorial.R
import com.adnagu.kotlintutorial.databinding.FragmentNotificationsBinding
import com.adnagu.kotlintutorial.model.Notification
import com.adnagu.kotlintutorial.ui.adapter.NotificationAdapter

/**
 * Created on 8/22/2019
 * @author wmramazan
 */
class NotificationsFragment : Fragment() {

    private var adapter: NotificationAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentNotificationsBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_notifications,
            container,
            false)
        // Set the lifecycleOwner so DataBinding can observe LiveData
        binding.lifecycleOwner = viewLifecycleOwner

        adapter = NotificationAdapter()

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@NotificationsFragment.adapter
        }

        return binding.root
    }

    fun setNotifications(notifications: List<Notification>) {
        adapter?.notifications = notifications
    }

} 