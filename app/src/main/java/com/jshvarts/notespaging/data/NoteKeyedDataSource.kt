package com.jshvarts.notespaging.data

import android.arch.paging.ItemKeyedDataSource
import com.jshvarts.notespaging.domain.Note
import com.jshvarts.notespaging.domain.NotesRepository
import javax.inject.Inject

class NoteKeyedDataSource @Inject constructor(
        private val notesRepo: NotesRepository
) : ItemKeyedDataSource<String, Note>() {

    override fun loadInitial(params: LoadInitialParams<String>, callback: LoadInitialCallback<Note>) {
        val items = notesRepo.notes(limit = params.requestedLoadSize)
        callback.onResult(items)
    }

    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<Note>) {
        val items = notesRepo.notesAfter(noteText = params.key, limit = params.requestedLoadSize)
        callback.onResult(items)
    }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<Note>) {
    }

    override fun getKey(item: Note) = item.text
}