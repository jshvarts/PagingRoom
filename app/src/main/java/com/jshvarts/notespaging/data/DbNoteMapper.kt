package com.jshvarts.notespaging.data

import com.jshvarts.notespaging.domain.Note

class DbNoteMapper constructor() {
    fun fromDb(from: NoteEntity) = Note(from.id, from.noteText)
    fun toDb(from: Note) = NoteEntity(from.id, from.text)
}