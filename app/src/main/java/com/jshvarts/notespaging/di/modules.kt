package com.jshvarts.notespaging.di

import com.jshvarts.notespaging.data.DbNoteMapper
import com.jshvarts.notespaging.data.NotesDatabase
import com.jshvarts.notespaging.data.NotesRepositoryImpl
import com.jshvarts.notespaging.domain.AddNoteUseCase
import com.jshvarts.notespaging.domain.GetNoteUseCase
import com.jshvarts.notespaging.domain.NoteKeyedDataSource
import com.jshvarts.notespaging.domain.NotesDataSourceFactory
import com.jshvarts.notespaging.domain.NotesRepository
import com.jshvarts.notespaging.presentation.addnote.AddNoteViewModel
import com.jshvarts.notespaging.presentation.addnote.AddNoteViewModelFactory
import com.jshvarts.notespaging.presentation.notedetail.NoteDetailViewModel
import com.jshvarts.notespaging.presentation.notedetail.NoteDetailViewModelFactory
import com.jshvarts.notespaging.presentation.notelist.NoteListViewModel
import com.jshvarts.notespaging.presentation.notelist.NoteListViewModelFactory
import org.koin.dsl.module.module

val notesModule = module {
    single { NotesDatabase.getInstance(get()) }
    single { get<NotesDatabase>().notesDao() }
    single { NotesRepositoryImpl(get(), get()) as NotesRepository }

    single { NoteDetailViewModelFactory(get()) }
    single { AddNoteViewModelFactory(get()) }
    single { NoteKeyedDataSource(get()) }
    single { NotesDataSourceFactory(get()) }
    single { NoteListViewModelFactory(get()) }

    single { AddNoteViewModel(get()) }
    single { NoteDetailViewModel(get()) }
    single { NoteListViewModel(get()) }

    factory { GetNoteUseCase(get()) }
    factory { AddNoteUseCase(get()) }
    factory { DbNoteMapper() }
}
