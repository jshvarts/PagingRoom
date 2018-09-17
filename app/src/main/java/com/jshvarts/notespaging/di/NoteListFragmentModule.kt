package com.jshvarts.notespaging.di

import com.jshvarts.notespaging.domain.GetNotesUseCase
import com.jshvarts.notespaging.domain.NotesRepository
import com.jshvarts.notespaging.presentation.notelist.NoteListViewModelFactory
import dagger.Module
import dagger.Provides

@Module
object NoteListFragmentModule {
    @JvmStatic
    @Provides
    fun provideNoteListViewModelFactory(getNotesUseCase: GetNotesUseCase) =
            NoteListViewModelFactory(getNotesUseCase)

    @JvmStatic
    @Provides
    fun provideGetNotesUseCase(notesRepo: NotesRepository) = GetNotesUseCase(notesRepo)
}