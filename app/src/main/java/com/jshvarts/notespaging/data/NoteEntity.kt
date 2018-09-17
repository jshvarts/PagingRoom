package com.jshvarts.notespaging.data

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "notes")
data class NoteEntity(
        @PrimaryKey(autoGenerate = true) val id: Long,
        @ColumnInfo(name = "note_text") val noteText: String)