package com.example.rickandmortyretrofitnew

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.rickandmortyretrofitnew.databinding.FragmentCharactersBinding
import com.example.rickandmortyretrofitnew.network.Character
import com.google.android.material.snackbar.Snackbar


class CharactersFragment : Fragment() {

    private var _binding: FragmentCharactersBinding? = null
    private val binding get() = _binding!!

    private val viewModel : MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.characterLiveData.observe(this) { state ->
            processCharacterResponse(state)

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCharactersBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun processCharacterResponse(state: ScreenState<List<Character>?>) {

        val pb = binding.progressBar

        when(state) {
            is ScreenState.Loading -> {
                pb.visibility = View.VISIBLE
            }
            is ScreenState.Success -> {
                pb.visibility = View.GONE

                if(state.data != null) {

                    val adapter = MainAdapter(state.data)
                    val recyclerView = binding.charactersRv

                    recyclerView?.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
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