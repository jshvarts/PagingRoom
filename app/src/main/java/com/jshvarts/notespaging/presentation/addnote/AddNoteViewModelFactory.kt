package com.jshvarts.notespaging.presentation.addnote

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModel
import com.jshvarts.notespaging.domain.AddNoteUseCase

class AddNoteViewModelFactory(
        private val addNoteUseCase: AddNoteUseCase
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddNoteViewModel::class.java)) {
            return AddNoteViewModel(addNoteUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}