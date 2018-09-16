package com.jshvarts.notespaging.di

import android.app.Activity
import android.app.Application
import com.jshvarts.notespaging.presentation.MainActivityModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.DispatchingAndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [
    MainActivityModule::class,
    DataModule::class,
    AndroidInjectionModule::class
])
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    val activityInjector: DispatchingAndroidInjector<Activity>
}

fun Application.createAppComponent() = DaggerAppComponent.builder()
        .application(this)
        .build()
