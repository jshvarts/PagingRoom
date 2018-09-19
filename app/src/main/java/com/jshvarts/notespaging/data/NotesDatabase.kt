package com.jshvarts.notespaging.data

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.jshvarts.notespaging.ioThread

@Database(
        entities = [NoteEntity::class],
        version = 1,
        exportSchema = false
)
abstract class NotesDatabase : RoomDatabase() {

    abstract fun notesDao(): NotesDao

    companion object {
        fun getInstance(context: Context): NotesDatabase =
                Room.databaseBuilder(context, NotesDatabase::class.java, "notesdb")
                        .addCallback(object : RoomDatabase.Callback() {
                            override fun onCreate(db: SupportSQLiteDatabase) {
                                fillInDb(context)
                            }
                        }).build()

        /**
         * fill database with list of notes so we can test pagination
         */
        private fun fillInDb(context: Context) {
            // inserts in Room are executed on the current thread, so we insert in the background
            ioThread {
                NotesDatabase.getInstance(context).notesDao().insertAll(
                        getData().map { NoteEntity(id = 0, noteText = it) }
                )
            }
        }
    }
}

private fun getData(): List<String> {
    return (1..100).map { "note" }
}
