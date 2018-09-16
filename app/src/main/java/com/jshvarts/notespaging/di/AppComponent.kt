package com.jshvarts.notespaging.di

import android.app.Activity
import dagger.android.DispatchingAndroidInjector

interface AppComponent {
    val activityInjector: DispatchingAndroidInjector<Activity>
}