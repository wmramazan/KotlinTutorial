package com.adnagu.kotlintutorial.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*
import com.adnagu.kotlintutorial.model.Notification
import com.adnagu.kotlintutorial.model.Post

/**
 * Created on 8/16/2019
 * @author wmramazan
 */
@Dao
interface NotificationDao {
    @Query("select * from notification")
    fun getAll(): LiveData<List<Notification>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(videos: List<Notification>)

    @Query("delete from notification")
    fun deleteAll()
}

@Dao
interface PostDao {
    @Query("select * from post")
    fun getAll(): LiveData<List<Post>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(videos: List<Post>)

    @Query("delete from post")
    fun deleteAll()
}

@Database(entities = [Notification::class, Post::class], version = 1, exportSchema = false)
abstract class KotlinTutorialDatabase: RoomDatabase() {
    abstract val notificationDao: NotificationDao
    abstract val postDao: PostDao
}

private lateinit var INSTANCE: KotlinTutorialDatabase

fun getDatabase(context: Context): KotlinTutorialDatabase {
    synchronized(KotlinTutorialDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(context.applicationContext,
                KotlinTutorialDatabase::class.java,
                "tutorial")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build()
        }
    }
    return INSTANCE
}