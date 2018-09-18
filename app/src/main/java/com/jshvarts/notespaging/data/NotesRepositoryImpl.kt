package com.jshvarts.notespaging.data

import android.arch.paging.DataSource
import com.jshvarts.notespaging.domain.Note
import com.jshvarts.notespaging.domain.NotesRepository
import io.reactivex.Completable
import io.reactivex.Single

class NotesRepositoryImpl(private val notesDao: NotesDao,
                          private val mapper: DbNoteMapper) : NotesRepository {

    override fun insert(note: Note): Completable =
            Completable.fromAction { notesDao.insert(mapper.toDb(note)) }

    override fun insertAll(notes: List<Note>): Completable =
            Completable.fromAction {
                notesDao.insertAll(notes.map { mapper.toDb(it) })
            }

    override fun noteById(id: Long): Single<Note> =
            notesDao.noteById(id).map { mapper.fromDb(it) }

    override fun allNotes(): DataSource.Factory<Int, Note> =
            notesDao.allNotes().map { mapper.fromDb(it) }
}