package com.jshvarts.notespaging.presentation.notelist

import android.arch.paging.PagedListAdapter
import android.support.v7.util.DiffUtil
import android.view.ViewGroup
import com.jshvarts.notespaging.domain.Note
import timber.log.Timber

typealias ClickListener = (Note) -> Unit

class NoteAdapter(
        private val clickListener: ClickListener
) : PagedListAdapter<Note, NoteViewHolder>(diffCallback) {

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        Timber.d("Binding view holder at position $position")
        val note = getItem(position)

        with(holder) {
            bindTo(note)
            note?.let {
                itemView.setOnClickListener {
                    clickListener(note)
                }
            }
        }

        Timber.d("james " + currentList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder =
            NoteViewHolder(parent)

    companion object {
        /**
         * This diff callback informs the PagedListAdapter how to compute list differences when new
         * PagedLists arrive.
         */
        private val diffCallback = object : DiffUtil.ItemCallback<Note>() {
            override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean =
                    oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean =
                    oldItem == newItem
        }
    }
}