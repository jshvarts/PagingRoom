package com.jshvarts.notespaging.presentation.notelist

import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import com.jshvarts.notespaging.domain.NotesDataSourceFactory
import com.jshvarts.notespaging.domain.Note
import com.jshvarts.notespaging.presentation.BaseViewModel
import javax.inject.Inject

private const val PAGE_SIZE = 10
private const val INITIAL_LOAD_SIZE_HINT = 25

class NoteListViewModel @Inject constructor(
        dataSourceFactory: NotesDataSourceFactory
) : BaseViewModel() {

    private val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(INITIAL_LOAD_SIZE_HINT)
            .setPageSize(PAGE_SIZE)
            .build()

    val noteList = LivePagedListBuilder<String, Note>(dataSourceFactory, config).build()
}