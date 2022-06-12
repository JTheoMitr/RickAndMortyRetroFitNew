package com.example.rickandmortyretrofitnew

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.example.rickandmortyretrofitnew.databinding.RvItemBinding
import com.example.rickandmortyretrofitnew.network.Character

class CharactersAdapter(val charactersList: List<Character>) : RecyclerView.Adapter<CharactersAdapter.MainViewHolder>() {

    inner class MainViewHolder(private val itemBinding: RvItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bindData(character: Character) {


            itemBinding.name.text = character.name
            itemBinding.race.text = character.species
            itemBinding.origin.text = character.origin.name
            itemBinding.image.load(character.image) {
                transformations(CircleCropTransformation())
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            RvItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bindData(charactersList[position])
    }

    override fun getItemCount(): Int {
        return charactersList.size
    }

}