package com.adnagu.kotlintutorial.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.adnagu.kotlintutorial.R
import com.adnagu.kotlintutorial.databinding.FragmentPostsBinding
import com.adnagu.kotlintutorial.model.Post
import com.adnagu.kotlintutorial.ui.adapter.PostAdapter

/**
 * Created on 8/22/2019
 * @author wmramazan
 */
class PostsFragment : Fragment() {

    private var adapter: PostAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentPostsBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_posts,
            container,
            false)
        // Set the lifecycleOwner so DataBinding can observe LiveData
        binding.lifecycleOwner = viewLifecycleOwner

        adapter = PostAdapter()

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@PostsFragment.adapter
        }

        return binding.root
    }

    fun setPosts(posts: List<Post>) {
        adapter?.posts = posts
    }

}