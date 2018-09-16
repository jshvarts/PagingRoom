package com.jshvarts.notespaging.presentation.notelist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.jshvarts.notespaging.R
import com.jshvarts.notespaging.domain.Note
import com.jshvarts.notespaging.presentation.notelist.NoteListFragmentDirections.actionNotesToAddNote
import com.jshvarts.notespaging.presentation.notelist.NoteListFragmentDirections.actionNotesToNoteDetail
import kotlinx.android.synthetic.main.note_list_fragment.*


class NoteListFragment : Fragment() {

    private val clickListener: ClickListener = this::onNoteClicked

    private val recyclerViewAdapter = NoteAdapter(clickListener)

    private lateinit var viewModel: NoteListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.note_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        viewModel = ViewModelProviders.of(this).get(NoteListViewModel::class.java)
        viewModel.observableNoteList.observe(this, Observer { notes ->
            notes?.let { render(notes) }
        })

        fab.setOnClickListener {
            findNavController(it).navigate(actionNotesToAddNote())
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.load()
    }

    private fun render(noteList: List<Note>) {
        recyclerViewAdapter.updateNotes(noteList)
        if (noteList.isEmpty()) {
            notesRecyclerView.visibility = View.GONE
            notesNotFound.visibility = View.VISIBLE
        } else {
            notesRecyclerView.visibility = View.VISIBLE
            notesNotFound.visibility = View.GONE
        }
    }

    private fun onNoteClicked(note: Note) {
        val navDirections = actionNotesToNoteDetail(note.id)
        view?.let {
            findNavController(it).navigate(navDirections)
        }
    }

    private fun setupRecyclerView() {
        notesRecyclerView.layoutManager = LinearLayoutManager(this.context)
        notesRecyclerView.adapter = recyclerViewAdapter
        notesRecyclerView.setHasFixedSize(true)
    }
}