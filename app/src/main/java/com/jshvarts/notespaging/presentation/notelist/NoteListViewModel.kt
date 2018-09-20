package com.jshvarts.notespaging.presentation.notelist

import android.arch.paging.PagedList
import android.arch.paging.RxPagedListBuilder
import com.jshvarts.notespaging.domain.GetNotesUseCase
import com.jshvarts.notespaging.domain.Note
import com.jshvarts.notespaging.presentation.BaseViewModel
import io.reactivex.Observable
import javax.inject.Inject

private const val PAGE_SIZE = 10

class NoteListViewModel @Inject constructor(
        getNotesUseCase: GetNotesUseCase
) : BaseViewModel() {

    val noteList: Observable<PagedList<Note>> =
            RxPagedListBuilder(getNotesUseCase.allNotes(), PAGE_SIZE)
                    .buildObservable()
}