package com.jshvarts.notespaging.domain

import io.reactivex.Single

class GetNotesUseCase(private val notesRepo: NotesRepository) {
    fun allNotes(): Single<List<Note>> = notesRepo.allNotes()
}