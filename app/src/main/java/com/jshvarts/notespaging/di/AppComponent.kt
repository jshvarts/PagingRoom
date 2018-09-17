package com.jshvarts.notespaging.di

import android.app.Activity
import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    DataModule::class,
    MainActivityModule::class,
    FragmentsModule::class,
    AndroidSupportInjectionModule::class
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
