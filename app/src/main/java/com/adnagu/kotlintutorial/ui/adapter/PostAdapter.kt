package com.adnagu.kotlintutorial.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.adnagu.kotlintutorial.R
import com.adnagu.kotlintutorial.databinding.ItemPostBinding
import com.adnagu.kotlintutorial.model.Post

/**
 * Created on 8/22/2019
 * @author wmramazan
 */
class PostAdapter : RecyclerView.Adapter<PostViewHolder>() {
    var posts: List<Post> = emptyList()
        set(value) {
            field = value

            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val withDataBinding: ItemPostBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            PostViewHolder.LAYOUT,
            parent,
            false)
        return PostViewHolder(withDataBinding)
    }

    override fun getItemCount(): Int = posts.size

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.post = posts[position]
            it.executePendingBindings()
        }
    }
}

class PostViewHolder(val viewDataBinding: ItemPostBinding) :
    RecyclerView.ViewHolder(viewDataBinding.root) {
    companion object {
        @LayoutRes
        val LAYOUT = R.layout.item_post
    }
}