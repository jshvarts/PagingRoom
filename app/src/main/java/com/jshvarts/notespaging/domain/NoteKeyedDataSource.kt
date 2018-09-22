package com.jshvarts.notespaging.domain

import android.arch.paging.ItemKeyedDataSource
import javax.inject.Inject

class NoteKeyedDataSource @Inject constructor(
        private val notesRepo: NotesRepository
) : ItemKeyedDataSource<String, Note>() {

    override fun loadInitial(params: LoadInitialParams<String>, callback: LoadInitialCallback<Note>) {
        val items = notesRepo.notes(requestedLoadSize = params.requestedLoadSize)
        callback.onResult(items)
    }

    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<Note>) {
        val items = notesRepo.notesAfter(key = params.key, requestedLoadSize = params.requestedLoadSize)
        callback.onResult(items)
    }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<Note>) {
    }

    override fun getKey(item: Note) = item.text
}