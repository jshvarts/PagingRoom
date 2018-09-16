package com.jshvarts.notespaging

import android.app.Application
import com.jshvarts.notespaging.di.AppComponent
import com.jshvarts.notespaging.di.createAppComponent
import dagger.android.HasActivityInjector
import timber.log.Timber

class PagingRoomApplication : Application(), HasActivityInjector {
    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        appComponent = createAppComponent()
    }

    override fun activityInjector() = appComponent.activityInjector
}