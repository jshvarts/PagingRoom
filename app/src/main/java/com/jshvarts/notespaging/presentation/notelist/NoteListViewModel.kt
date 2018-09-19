package com.jshvarts.notespaging.presentation.notelist

import android.arch.lifecycle.LiveData
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import com.jshvarts.notespaging.domain.GetNotesUseCase
import com.jshvarts.notespaging.domain.Note
import com.jshvarts.notespaging.presentation.BaseViewModel
import javax.inject.Inject

private const val PAGE_SIZE = 10

class NoteListViewModel @Inject constructor(
        getNotesUseCase: GetNotesUseCase
) : BaseViewModel() {

    val noteList: LiveData<PagedList<Note>> =
            LivePagedListBuilder(
                    getNotesUseCase.allNotes(), PAGE_SIZE)
                    .build()

// For more control including loading view (placeholders):
//    val pagedListConfig = PagedList.Config.Builder()
//            .setEnablePlaceholders(true)
//            .setInitialLoadSizeHint(PAGE_SIZE)
//            .setPageSize(PAGE_SIZE)
//            .build()
//    val noteList = LivePagedListBuilder<Int,Note>(getNotesUseCase.allNotes(), pagedListConfig).build()

}