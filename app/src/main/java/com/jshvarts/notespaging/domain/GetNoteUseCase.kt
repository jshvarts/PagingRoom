package com.jshvarts.notespaging.domain

import io.reactivex.Single
import javax.inject.Inject

class GetNoteUseCase @Inject constructor(private val notesRepo: NotesRepository) {
    fun getNote(id: Long): Single<Note> = notesRepo.noteById(id)
}