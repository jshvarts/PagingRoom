package com.jshvarts.notespaging.di

import com.jshvarts.notespaging.presentation.addnote.AddNoteFragment
import com.jshvarts.notespaging.presentation.notedetail.NoteDetailFragment
import com.jshvarts.notespaging.presentation.notelist.NoteListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentsModule {
    @ContributesAndroidInjector(modules = [NoteListFragmentModule::class])
    abstract fun contributeNoteListFragment(): NoteListFragment

    @ContributesAndroidInjector(modules = [NoteDetailFragmentModule::class])
    abstract fun contributeNoteDetailFragment(): NoteDetailFragment

    @ContributesAndroidInjector(modules = [AddNoteFragmentModule::class])
    abstract fun contributeAddNoteFragment(): AddNoteFragment
}