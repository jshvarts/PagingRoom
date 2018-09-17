package com.jshvarts.notespaging.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

@Database(
        entities = [NoteEntity::class],
        version = 1,
        exportSchema = false
)
abstract class NotesDatabase : RoomDatabase() {
    companion object {
        fun getInstance(context: Context): NotesDatabase =
                Room.databaseBuilder(context, NotesDatabase::class.java, "notesdb")
                        .build()
    }

    abstract fun notesDao(): NotesDao
}