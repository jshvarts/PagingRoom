package com.jshvarts.notespaging.di

import dagger.Module
import dagger.Provides

@Module
object DataModule {
    @JvmStatic
    @Provides
    fun provideUHelloWorldMessage() = HelloWorldProvider()
}