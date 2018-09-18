package com.jshvarts.notespaging

import android.app.Application
import com.jshvarts.notespaging.di.AppComponent
import com.jshvarts.notespaging.di.DaggerAppComponent
import dagger.android.HasActivityInjector
import timber.log.Timber

class PagingRoomApplication : Application(), HasActivityInjector {
    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        appComponent = DaggerAppComponent.builder()
                .application(this)
                .build()
    }

    override fun activityInjector() = appComponent.activityInjector
}