package com.jshvarts.notespaging.di

import android.app.Application
import android.content.Context
import com.jshvarts.notespaging.data.DbNoteMapper
import com.jshvarts.notespaging.data.NotesDao
import com.jshvarts.notespaging.data.NotesDatabase
import com.jshvarts.notespaging.data.NotesRepositoryImpl
import com.jshvarts.notespaging.domain.NotesRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object DataModule {
    @Singleton
    @JvmStatic
    @Provides
    fun provideContext(application: Application) = application.applicationContext

    @Singleton
    @JvmStatic
    @Provides
    fun provideNotesDao(context: Context) = NotesDatabase.getInstance(context).notesDao()

    @Singleton
    @JvmStatic
    @Provides
    fun provideRepository(notesDao: NotesDao, mapper: DbNoteMapper): NotesRepository = NotesRepositoryImpl(notesDao, mapper)
}