package com.jshvarts.notespaging.domain

import io.reactivex.Single
import javax.inject.Inject

class GetNotesUseCase @Inject constructor(private val notesRepo: NotesRepository) {
    fun allNotes(): Single<List<Note>> = notesRepo.allNotes()
}