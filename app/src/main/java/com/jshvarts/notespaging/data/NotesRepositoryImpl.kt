package com.jshvarts.notespaging.data

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

    override fun notes(limit: Int): List<Note> =
            notesDao.notes(limit).map { mapper.fromDb(it) }

    override fun notesAfter(noteText: String, limit: Int): List<Note> =
            notesDao.notesAfter(noteText, limit).map { mapper.fromDb(it) }
}