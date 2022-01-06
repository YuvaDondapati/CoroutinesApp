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
import com.yuvademos.coroutinesapp.databinding.FragmentPlaylistBinding
import com.yuvademos.coroutinesapp.viewmodel.PlayListViewModel
import com.yuvademos.coroutinesapp.viewmodel.PlayListViewModelFactory
import org.koin.android.ext.android.inject

class PlayListFragment : Fragment() {

    private lateinit var viewModel: PlayListViewModel

    val viewModelFactory: PlayListViewModelFactory by inject()
    private var _binding: FragmentPlaylistBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPlaylistBinding.inflate(inflater, container, false)
        val view = binding?.root
        setUpViewModel()
        observeViewModel()
        return view
    }

    private fun observeViewModel() {
        viewModel.loader.observe(viewLifecycleOwner, { loading ->
            when {
                loading -> binding?.progressBar?.visibility = View.VISIBLE
                else -> {
                    binding?.progressBar?.visibility = View.GONE
                }
            }
        })
        viewModel.playLists.observe(viewLifecycleOwner, { playlists ->
            when {
                playlists.isSuccess -> {
                    binding?.playListsList?.apply {
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
        viewModel = ViewModelProvider(this, viewModelFactory).get(PlayListViewModel::class.java)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            PlayListFragment().apply {}
    }

}