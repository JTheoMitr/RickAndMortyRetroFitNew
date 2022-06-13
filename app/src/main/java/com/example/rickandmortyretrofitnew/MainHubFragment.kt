package com.example.rickandmortyretrofitnew

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.rickandmortyretrofitnew.databinding.FragmentCharactersBinding
import com.example.rickandmortyretrofitnew.databinding.FragmentMainHubBinding


class MainHubFragment : Fragment() {

    private var _binding: FragmentMainHubBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onResume() {
        super.onResume()
        adjustTitle("Welcome, User")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMainHubBinding.inflate(inflater, container, false)

        binding.btnCharacters.setOnClickListener {
            findNavController().navigate(
            R.id.action_mainHubFragment2_to_charactersFragment
            )
        }

        return binding.root

    }

    private fun adjustTitle(title: String) {

        (activity as AppCompatActivity).supportActionBar?.title = title

    }

}