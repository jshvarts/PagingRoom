package com.jshvarts.notespaging.domain

import javax.inject.Inject

class GetNotesUseCase @Inject constructor(private val notesRepo: NotesRepository) {
    fun allNotes() = notesRepo.allNotes()
}