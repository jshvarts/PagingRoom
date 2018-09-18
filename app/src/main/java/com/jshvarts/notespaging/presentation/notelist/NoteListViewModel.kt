package com.jshvarts.notespaging.presentation.notelist

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.jshvarts.notespaging.domain.GetNotesUseCase
import com.jshvarts.notespaging.domain.Note
import com.jshvarts.notespaging.presentation.BaseViewModel
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class NoteListViewModel @Inject constructor(
        private val getNotesUseCase: GetNotesUseCase
) : BaseViewModel() {
    private val noteList = MutableLiveData<List<Note>>()

    val observableNoteList: LiveData<List<Note>>
        get() = noteList

    fun load() {
        disposables += getNotesUseCase.allNotes()
                .subscribeOn(Schedulers.io())
                .subscribeBy(onSuccess = noteList::postValue, onError = Timber::e)
    }
}