package com.jshvarts.notespaging.domain

import io.reactivex.Completable
import io.reactivex.Single

interface NotesRepository {
    fun insert(note: Note): Completable

    fun insertAll(notes: List<Note>): Completable

    fun noteById(id: Long): Single<Note>

    fun notes(requestedLoadSize: Int): List<Note>

    fun notesAfter(key: String, requestedLoadSize: Int): List<Note>
}