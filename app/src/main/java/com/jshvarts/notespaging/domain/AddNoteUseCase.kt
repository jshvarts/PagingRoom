package com.jshvarts.notespaging.domain

import io.reactivex.Completable

class AddNoteUseCase(private val notesRepo: NotesRepository) {
    fun addNote(note: Note): Completable = notesRepo.insert(note)
}