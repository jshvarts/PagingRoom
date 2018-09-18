package com.jshvarts.notespaging.presentation

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.jshvarts.notespaging.R
import com.jshvarts.notespaging.domain.Note
import com.jshvarts.notespaging.domain.NotesRepository
import dagger.android.support.AndroidSupportInjection
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.populate_db_fragment.*
import timber.log.Timber
import javax.inject.Inject

/**
 * This fragment accesses the data layer directly just for creating fake data we need for this demo.
 * Normally, all data layer interactions should go thru ViewModel -> Domain -> Data layer
 * (like the other fragments in this project)
 */
class PopulateDbFragment : Fragment() {
    @Inject
    lateinit var dataRepository: NotesRepository

    private val disposables = CompositeDisposable()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.populate_db_fragment, container, false)
    }

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        populateDbButton.setOnClickListener {
            disposables += dataRepository.insertAll(makeFakeData())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeBy(onComplete = {
                        findNavController().popBackStack()
                    }, onError = Timber::e)
        }
    }

    override fun onStop() {
        disposables.clear()
        super.onStop()

    }

    private fun makeFakeData(): List<Note> {
        var mutableList = mutableListOf<Note>()
        repeat(100) {
            mutableList.add(Note(text = "my note"))
        }
        return mutableList.toList()
    }
}