package com.jshvarts.notespaging.data

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import io.reactivex.Single

@Dao
interface NotesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(note: NoteEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(note: List<NoteEntity>)

    @Query("SELECT * FROM notes WHERE id = :id")
    fun noteById(id: Long): Single<NoteEntity>

    @Query("SELECT * FROM notes ORDER BY note_text ASC limit :requestedLoadSize")
    fun notes(requestedLoadSize: Int): List<NoteEntity>

    @Query("SELECT * FROM notes WHERE note_text > :key ORDER BY note_text ASC limit :requestedLoadSize")
    fun notesAfter(key: String, requestedLoadSize: Int): List<NoteEntity>
}