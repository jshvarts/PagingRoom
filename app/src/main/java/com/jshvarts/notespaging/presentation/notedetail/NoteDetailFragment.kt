package com.jshvarts.notespaging.presentation.notedetail

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jshvarts.notespaging.R
import com.jshvarts.notespaging.domain.Note
import com.jshvarts.notespaging.presentation.notedetail.NoteDetailFragmentArgs.fromBundle
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.note_detail_fragment.*
import javax.inject.Inject

class NoteDetailFragment : Fragment() {
    @Inject
    lateinit var noteDetailViewModelFactory: NoteDetailViewModelFactory

    private lateinit var viewModel: NoteDetailViewModel

    private val noteId by lazy {
        fromBundle(arguments).noteId
    }

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.note_detail_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this, noteDetailViewModelFactory).get(NoteDetailViewModel::class.java)
        viewModel.observableNote.observe(this, Observer { note ->
            note?.let { render(note) } ?: renderNoteNotFound()
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.getNote(noteId)
    }

    private fun render(note: Note) {
        noteIdView.text = String.format(getString(R.string.note_detail_id), note.id)
        noteText.text = String.format(getString(R.string.note_detail_text), note.text)
    }

    private fun renderNoteNotFound() {
        noteIdView.visibility = View.GONE
        noteText.visibility = View.GONE
        view?.let {
            Snackbar.make(it, R.string.error_loading_note, Snackbar.LENGTH_LONG).show()
        }
    }
}