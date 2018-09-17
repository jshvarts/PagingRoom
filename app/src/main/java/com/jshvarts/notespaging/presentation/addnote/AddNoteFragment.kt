package com.jshvarts.notespaging.presentation.addnote

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.navigation.Navigation.findNavController
import com.jshvarts.notespaging.R
import com.jshvarts.notespaging.presentation.closeSoftKeyboard
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.add_note_fragment.*
import javax.inject.Inject

class AddNoteFragment : Fragment() {
    @Inject
    lateinit var addNoteViewModelFactory: AddNoteViewModelFactory

    private lateinit var viewModel: AddNoteViewModel

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.add_note_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this, addNoteViewModelFactory).get(AddNoteViewModel::class.java)
        viewModel.observableStatus.observe(this, Observer { status ->
            status?.let { render(status) }
        })

        addNoteText.setOnEditorActionListener { v, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                viewModel.addNote(v.text.toString())
                v.closeSoftKeyboard()
                true
            } else {
                false
            }
        }
    }

    private fun render(status: Boolean) {
        when (status) {
            true -> {
                view?.let {
                    findNavController(it).popBackStack()
                }
            }
            false -> addNoteText.error = getString(R.string.error_validating_note)
        }
    }
}