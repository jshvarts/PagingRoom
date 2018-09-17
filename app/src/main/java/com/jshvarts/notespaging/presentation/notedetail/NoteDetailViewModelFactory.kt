package com.jshvarts.notespaging.presentation.notedetail

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.jshvarts.notespaging.domain.GetNoteUseCase

class NoteDetailViewModelFactory(
        private val getNoteUseCase: GetNoteUseCase
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NoteDetailViewModel::class.java)) {
            return NoteDetailViewModel(getNoteUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}