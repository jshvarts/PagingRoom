package com.jshvarts.notespaging.domain

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.DataSource
import javax.inject.Inject

class NotesDataSourceFactory @Inject constructor(
        private val dataSource: NoteKeyedDataSource
) : DataSource.Factory<String, Note>() {

    private val notesLiveData = MutableLiveData<NoteKeyedDataSource>()

    override fun create(): DataSource<String, Note> {
        notesLiveData.postValue(dataSource)
        return dataSource
    }
}