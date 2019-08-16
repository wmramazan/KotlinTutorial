package com.adnagu.kotlintutorial

import android.app.Application
import timber.log.Timber

/**
 * Created on 8/16/2019
 * @author wmramazan
 */
class KotlinTutorialApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())
    }

}