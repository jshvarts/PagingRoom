package com.jshvarts.notespaging.presentation.notelist

import android.support.v7.util.DiffUtil.calculateDiff
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.jshvarts.notespaging.R
import com.jshvarts.notespaging.domain.Note

typealias ClickListener = (Note) -> Unit

class NoteAdapter(
        private val clickListener: ClickListener
) : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

    private var noteList = emptyList<Note>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemContainer = LayoutInflater.from(parent.context)
                .inflate(R.layout.note_item, parent, false) as ViewGroup
        val viewHolder = ViewHolder(itemContainer)
        itemContainer.setOnClickListener {
            clickListener(noteList[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note = noteList[position]
        holder.id.text = note.id.toString()
        holder.text.text = note.text
    }

    override fun getItemCount() = noteList.size

    fun updateNotes(noteList: List<Note>) {
        val diffResult = calculateDiff(NoteDiffCallback(this.noteList, noteList))
        this.noteList = noteList
        diffResult.dispatchUpdatesTo(this)
    }

    class ViewHolder(itemViewGroup: ViewGroup) : RecyclerView.ViewHolder(itemViewGroup) {
        val id: TextView = itemViewGroup.findViewById(R.id.noteId)
        val text: TextView = itemViewGroup.findViewById(R.id.noteText)
    }
}