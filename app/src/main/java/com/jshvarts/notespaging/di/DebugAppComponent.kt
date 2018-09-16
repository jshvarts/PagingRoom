package com.jshvarts.notespaging.di

import android.app.Application
import com.jshvarts.notespaging.presentation.MainActivityModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    MainActivityModule::class,
    DataModule::class,
    AndroidInjectionModule::class
])
interface DebugAppComponent : AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): DebugAppComponent
    }
}

fun Application.createAppComponent() = DaggerDebugAppComponent.builder()
        .application(this)
        .build()