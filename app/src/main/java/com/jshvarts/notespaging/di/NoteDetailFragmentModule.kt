package com.jshvarts.notespaging.di

import com.jshvarts.notespaging.domain.GetNoteUseCase
import com.jshvarts.notespaging.domain.NotesRepository
import com.jshvarts.notespaging.presentation.notedetail.NoteDetailViewModelFactory
import dagger.Module
import dagger.Provides

@Module
object NoteDetailFragmentModule {
    @JvmStatic
    @Provides
    fun provideNoteDetailViewModelFactory(getNoteUseCase: GetNoteUseCase) = NoteDetailViewModelFactory(getNoteUseCase)

    @JvmStatic
    @Provides
    fun provideGetNoteUseCase(notesRepo: NotesRepository) = GetNoteUseCase(notesRepo)
}