package com.jshvarts.notespaging.domain

import io.reactivex.Completable
import javax.inject.Inject

class AddNoteUseCase @Inject constructor(private val notesRepo: NotesRepository) {
    fun addNote(note: Note): Completable = notesRepo.insert(note)
}