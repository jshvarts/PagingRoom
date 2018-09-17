package com.jshvarts.notespaging.domain

import io.reactivex.Single

class GetNoteUseCase(private val notesRepo: NotesRepository) {
    fun getNote(id: Long): Single<Note> = notesRepo.noteById(id)
}