package com.jshvarts.notespaging.presentation.notelist

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.jshvarts.notespaging.R
import com.jshvarts.notespaging.domain.Note

class NoteViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
) {
    private val idView = itemView.findViewById<TextView>(R.id.noteId)
    private val nameView = itemView.findViewById<TextView>(R.id.noteText)
    private var note: Note? = null

    /**
     * Items might be null if they are not paged in yet. PagedListAdapter will re-bind the
     * ViewHolder when Item is loaded.
     */
    fun bindTo(note: Note?) {
        this.note = note
        idView.text = note?.let { it.id.toString() } ?: ""
        nameView.text = note?.text ?: ""
    }
}