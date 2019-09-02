package com.adnagu.kotlintutorial.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.adnagu.kotlintutorial.R
import com.adnagu.kotlintutorial.databinding.ItemNotificationBinding
import com.adnagu.kotlintutorial.model.Notification

/**
 * Created on 8/22/2019
 * @author wmramazan
 */
class NotificationAdapter : RecyclerView.Adapter<NotificationViewHolder>() {
    var notifications: List<Notification> = emptyList()
        set(value) {
            field = value

            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        val withDataBinding: ItemNotificationBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            NotificationViewHolder.LAYOUT,
            parent,
            false)
        return NotificationViewHolder(withDataBinding)
    }

    override fun getItemCount(): Int = notifications.size

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.notification = notifications[position]
            it.executePendingBindings()
        }
    }
}

class NotificationViewHolder(val viewDataBinding: ItemNotificationBinding) :
    RecyclerView.ViewHolder(viewDataBinding.root) {
    companion object {
        @LayoutRes
        val LAYOUT = R.layout.item_notification
    }
}