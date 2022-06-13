package com.example.rickandmortyretrofitnew

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.rickandmortyretrofitnew.databinding.ActivityEpisodesBinding
import com.example.rickandmortyretrofitnew.episodes.EpisodeViewModel
import com.example.rickandmortyretrofitnew.episodes.EpisodesAdapter
import com.example.rickandmortyretrofitnew.network.Episode
import com.google.android.material.snackbar.Snackbar

class EpisodesActivity : AppCompatActivity() {
    private var _binding: ActivityEpisodesBinding? = null
    private val binding get() = _binding!!

    private val viewModel : EpisodeViewModel by lazy {
        ViewModelProvider(this).get(EpisodeViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityEpisodesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.episodesLiveData.observe(this) { state ->
            processEpisodeResponse(state)

        }

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
                    val recyclerView = findViewById<RecyclerView>(R.id.episodesRv)

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