package com.jshvarts.notespaging.domain

import io.reactivex.Completable
import io.reactivex.Single

interface NotesRepository {
    fun insert(note: Note): Completable

    fun insertAll(notes: List<Note>): Completable

    fun noteById(id: Long): Single<Note>

    fun notes(limit: Int): List<Note>

    fun notesAfter(noteText: String, limit: Int): List<Note>
}