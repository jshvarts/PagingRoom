package com.jshvarts.notespaging.di

import com.jshvarts.notespaging.domain.AddNoteUseCase
import com.jshvarts.notespaging.domain.NotesRepository
import com.jshvarts.notespaging.presentation.addnote.AddNoteViewModelFactory
import dagger.Module
import dagger.Provides

@Module
object AddNoteFragmentModule {
    @JvmStatic
    @Provides
    fun provideAddNoteViewModelFactory(addNoteUseCase: AddNoteUseCase) = AddNoteViewModelFactory(addNoteUseCase)

    @JvmStatic
    @Provides
    fun provideAddNotesUseCase(notesRepo: NotesRepository) = AddNoteUseCase(notesRepo)
}