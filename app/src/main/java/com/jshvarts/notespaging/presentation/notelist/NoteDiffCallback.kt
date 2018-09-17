package com.jshvarts.notespaging.presentation.notelist

import android.support.v7.util.DiffUtil
import com.jshvarts.notespaging.domain.Note

class NoteDiffCallback(private val old: List<Note>,
                       private val new: List<Note>) : DiffUtil.Callback() {
    override fun getOldListSize() = old.size

    override fun getNewListSize() = new.size

    override fun areItemsTheSame(oldIndex: Int, newIndex: Int): Boolean {
        return old[oldIndex].text == new[newIndex].text
    }

    override fun areContentsTheSame(oldIndex: Int, newIndex: Int): Boolean {
        return old[oldIndex] == new[newIndex]
    }
}