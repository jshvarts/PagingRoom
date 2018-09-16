package com.jshvarts.notespaging.presentation.deletenote

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
import com.jshvarts.notespaging.presentation.deletenote.DeleteNoteFragmentArgs.fromBundle
import kotlinx.android.synthetic.main.delete_note_fragment.*

class DeleteNoteFragment : Fragment() {

    private lateinit var viewModel: DeleteNoteViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.delete_note_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(DeleteNoteViewModel::class.java)
        viewModel.observableCurrentNote.observe(this, Observer { currentNote ->
            currentNote?.let { initCurrentNote(currentNote) }
        })
        viewModel.observableDeleteStatus.observe(this, Observer { deleteStatus ->
            deleteStatus?.let { render(deleteStatus) }
        })

        val args = fromBundle(arguments)
        viewModel.initNote(args.noteId)

        cancelDeleteButton.setOnClickListener {
            findNavController(it).popBackStack()
        }

        confirmDeleteButton.setOnClickListener {
            viewModel.deleteNote(args.noteId)
        }
    }

    private fun initCurrentNote(note: Note) {
        noteId.text = String.format(getString(R.string.note_detail_id), note.id)
        noteText.text = String.format(getString(R.string.note_detail_text), note.text)
    }

    private fun render(deleteStatus: Boolean) {
        when (deleteStatus) {
            true -> {
                view?.let {
                    findNavController(it).popBackStack(R.id.notesFragment, false)
                }
            }
            false -> Snackbar.make(confirmDeleteButton, R.string.error_deleting_note, Snackbar.LENGTH_LONG).show()
        }
    }
}