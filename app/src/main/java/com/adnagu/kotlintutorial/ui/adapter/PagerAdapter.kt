package com.adnagu.kotlintutorial.ui.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.adnagu.kotlintutorial.R
import com.adnagu.kotlintutorial.model.Notification
import com.adnagu.kotlintutorial.model.Post
import com.adnagu.kotlintutorial.ui.main.NotificationsFragment
import com.adnagu.kotlintutorial.ui.main.PostsFragment

private val TAB_TITLES = arrayOf(
    R.string.posts,
    R.string.notifications
)

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class PagerAdapter(private val context: Context, fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val postsFragment = PostsFragment()
    private val notificationsFragment = NotificationsFragment()

    private val fragments = listOf(
        postsFragment,
        notificationsFragment
    )

    override fun getItem(position: Int): Fragment = fragments[position]

    override fun getPageTitle(position: Int): CharSequence? = context.resources.getString(TAB_TITLES[position])

    override fun getCount(): Int = fragments.size

    fun setPosts(posts: List<Post>) {
        postsFragment.setPosts(posts)
    }

    fun setNotifications(notifications: List<Notification>) {
        notificationsFragment.setNotifications(notifications)
    }
}