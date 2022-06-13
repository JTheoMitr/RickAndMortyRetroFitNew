package com.example.rickandmortyretrofitnew.episodes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.rickandmortyretrofitnew.R
import com.example.rickandmortyretrofitnew.ScreenState
import com.example.rickandmortyretrofitnew.databinding.FragmentEpisodesBinding
import com.example.rickandmortyretrofitnew.network.Episode
import com.google.android.material.snackbar.Snackbar


class EpisodesFragment : Fragment() {

    private var _binding: FragmentEpisodesBinding? = null
    private val binding get() = _binding!!

    private val viewModel : EpisodeViewModel by lazy {
        ViewModelProvider(this).get(EpisodeViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.episodesLiveData.observe(this) { state ->
            processEpisodeResponse(state)

        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_episodes, container, false)
    }

    private fun processEpisodeResponse(state: ScreenState<List<Episode>?>) {

        val pb = binding.progressBar

        when(state) {
            is ScreenState.Loading -> {
                pb.visibility = View.VISIBLE
            }
            is ScreenState.Success -> {
                pb.visibility = View.GONE

                if(state.data != null) {

                    val adapter = EpisodesAdapter(state.data)
                    val recyclerView = binding.episodesRv

                    recyclerView?.layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
                    recyclerView?.adapter = adapter

                }
            }

            is ScreenState.Error -> {
                pb.visibility = View.GONE
                val view = pb.rootView
                Snackbar.make(view, state.message!!, Snackbar.LENGTH_LONG).show()

            }
        }
    }


}