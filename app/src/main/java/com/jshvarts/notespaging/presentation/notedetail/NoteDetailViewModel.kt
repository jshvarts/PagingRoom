package com.jshvarts.notespaging.presentation.notedetail

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.jshvarts.notespaging.domain.GetNoteUseCase
import com.jshvarts.notespaging.domain.Note
import com.jshvarts.notespaging.presentation.BaseViewModel
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class NoteDetailViewModel(
        private val getNoteUseCase: GetNoteUseCase
) : BaseViewModel() {
    private val note = MutableLiveData<Note>()

    val observableNote: LiveData<Note>
        get() = note

    fun getNote(id: Long) {
        disposables += getNoteUseCase.getNote(id)
                .subscribeOn(Schedulers.io())
                .subscribeBy(onSuccess = note::postValue, onError = Timber::e)
    }
}