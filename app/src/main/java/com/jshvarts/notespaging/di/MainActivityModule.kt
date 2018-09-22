package com.jshvarts.notespaging.di

import com.jshvarts.notespaging.data.NotesDataSourceFactory
import com.jshvarts.notespaging.domain.AddNoteUseCase
import com.jshvarts.notespaging.domain.GetNoteUseCase
import com.jshvarts.notespaging.presentation.MainActivity
import com.jshvarts.notespaging.presentation.addnote.AddNoteFragment
import com.jshvarts.notespaging.presentation.addnote.AddNoteViewModelFactory
import com.jshvarts.notespaging.presentation.notedetail.NoteDetailFragment
import com.jshvarts.notespaging.presentation.notedetail.NoteDetailViewModelFactory
import com.jshvarts.notespaging.presentation.notelist.NoteListFragment
import com.jshvarts.notespaging.presentation.notelist.NoteListViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityModule {
    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun contributeNoteListFragment(): NoteListFragment

    @ContributesAndroidInjector
    abstract fun contributeNoteDetailFragment(): NoteDetailFragment

    @ContributesAndroidInjector
    abstract fun contributeAddNoteFragment(): AddNoteFragment

    @Module
    companion object {
        @JvmStatic
        @Provides
        fun provideNoteDetailViewModelFactory(getNoteUseCase: GetNoteUseCase) =
                NoteDetailViewModelFactory(getNoteUseCase)

        @JvmStatic
        @Provides
        fun provideNoteListViewModelFactory(dataSourceFactory: NotesDataSourceFactory) =
                NoteListViewModelFactory(dataSourceFactory)

        @JvmStatic
        @Provides
        fun provideAddNoteViewModelFactory(addNoteUseCase: AddNoteUseCase) =
                AddNoteViewModelFactory(addNoteUseCase)
    }
}