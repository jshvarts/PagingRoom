package com.jshvarts.notespaging

import android.app.Application
import com.jshvarts.notespaging.di.notesModule
import org.koin.android.ext.android.startKoin
import timber.log.Timber

class PagingRoomApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin(this, listOf(notesModule))
    }
}