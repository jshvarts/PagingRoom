package com.jshvarts.notespaging.presentation.addnote

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.jshvarts.notespaging.domain.AddNoteUseCase
import com.jshvarts.notespaging.domain.Note
import com.jshvarts.notespaging.presentation.BaseViewModel
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class AddNoteViewModel constructor(
        private val addNoteUseCase: AddNoteUseCase
) : BaseViewModel() {
    private val status = MutableLiveData<Boolean>()

    val observableStatus: LiveData<Boolean>
        get() = status

    fun addNote(noteText: String) {
        disposables += addNoteUseCase.addNote(Note(text = noteText))
                .subscribeOn(Schedulers.io())
                .subscribeBy(onComplete = {
                    status.postValue(true)
                }, onError = {
                    Timber.e(it)
                    status.postValue(false)
                })
    }
}