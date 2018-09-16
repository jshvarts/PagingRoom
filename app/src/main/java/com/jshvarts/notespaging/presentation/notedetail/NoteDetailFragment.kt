package com.jshvarts.notespaging.presentation.notedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import com.jshvarts.notespaging.R
import com.jshvarts.notespaging.domain.Note
import com.jshvarts.notespaging.presentation.notedetail.NoteDetailFragmentArgs.fromBundle
import com.jshvarts.notespaging.presentation.notedetail.NoteDetailFragmentDirections.actionNoteDetailToDeleteNote
import com.jshvarts.notespaging.presentation.notedetail.NoteDetailFragmentDirections.actionNoteDetailToEditNote
import kotlinx.android.synthetic.main.note_detail_fragment.*

class NoteDetailFragment : Fragment() {

    private lateinit var viewModel: NoteDetailViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.note_detail_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(NoteDetailViewModel::class.java)
        viewModel.observableNote.observe(this, Observer { note ->
            note?.let { render(note) } ?: renderNoteNotFound()
        })

        val args = fromBundle(arguments)
        editNoteButton.setOnClickListener {
            val navDirections = actionNoteDetailToEditNote(args.noteId)
            findNavController(it).navigate(navDirections)
        }

        deleteNoteButton.setOnClickListener {
            val navDirections = actionNoteDetailToDeleteNote(args.noteId)
            findNavController(it).navigate(navDirections)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getNote(fromBundle(arguments).noteId)
    }

    private fun render(note: Note) {
        noteId.text = String.format(getString(R.string.note_detail_id), note.id)
        noteText.text = String.format(getString(R.string.note_detail_text), note.text)
    }

    private fun renderNoteNotFound() {
        noteId.visibility = View.GONE
        noteText.visibility = View.GONE
        view?.let {
            Snackbar.make(it, R.string.error_loading_note, Snackbar.LENGTH_LONG).show()
        }
    }
}