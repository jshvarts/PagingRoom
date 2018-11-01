package com.jshvarts.notespaging.presentation.notelist

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.arch.paging.PagedList
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation.findNavController
import com.jshvarts.notespaging.R
import com.jshvarts.notespaging.domain.Note
import com.jshvarts.notespaging.presentation.notelist.NoteListFragmentDirections.actionNotesToAddNote
import com.jshvarts.notespaging.presentation.notelist.NoteListFragmentDirections.actionNotesToNoteDetail
import kotlinx.android.synthetic.main.note_list_fragment.*
import org.koin.android.ext.android.inject

class NoteListFragment : Fragment() {

    private val noteListViewModelFactory: NoteListViewModelFactory by inject()

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

        viewModel = ViewModelProviders.of(this, noteListViewModelFactory).get(NoteListViewModel::class.java)
        viewModel.noteList.observe(this, Observer { pagedNoteList ->
            pagedNoteList?.let { render(pagedNoteList) }
        })

        fab.setOnClickListener {
            findNavController(it).navigate(actionNotesToAddNote())
        }
    }

    private fun render(pagedNoteList: PagedList<Note>) {
        recyclerViewAdapter.submitList(pagedNoteList)
        if (pagedNoteList.isEmpty()) {
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
    }
}