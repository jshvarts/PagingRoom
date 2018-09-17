package com.jshvarts.notespaging.presentation.notelist

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.jshvarts.notespaging.domain.GetNotesUseCase

class NoteListViewModelFactory(
        private val getNotesUseCase: GetNotesUseCase
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NoteListViewModel::class.java)) {
            return NoteListViewModel(getNotesUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}