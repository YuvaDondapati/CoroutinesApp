package com.yuvademos.coroutinesapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yuvademos.coroutinesapp.R
import com.yuvademos.coroutinesapp.repository.PlayListRepository
import com.yuvademos.coroutinesapp.viewmodel.PlayListViewModel
import com.yuvademos.coroutinesapp.viewmodel.PlayListViewmodelFactory

class PlayListFragment : Fragment() {

    private lateinit var viewModel: PlayListViewModel
    lateinit var viewModelFactory: PlayListViewmodelFactory
    private val repository = PlayListRepository()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_playlist, container, false)
        setUpViewModel()
        observeViewModel(view)
        return view
    }

    private fun observeViewModel(view: View?) {
        viewModel.playLists.observe(viewLifecycleOwner, Observer { playlists ->
            when {
                playlists.isSuccess -> {
                    with(view as RecyclerView) {
                        layoutManager = LinearLayoutManager(context)
                        adapter = playlists.getOrNull()?.let { MyPlayListRecyclerViewAdapter(it) }
                    }
                }
                else -> {
                    //TODO error case
                }
            }
        })
    }

    private fun setUpViewModel() {
        viewModelFactory = PlayListViewmodelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(PlayListViewModel::class.java)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            PlayListFragment().apply {}
    }
}