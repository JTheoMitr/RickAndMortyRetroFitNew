package com.example.rickandmortyretrofitnew

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.example.rickandmortyretrofitnew.databinding.RvItemBinding
import com.example.rickandmortyretrofitnew.network.Character

class MainAdapter(val charactersList: List<Character>) : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    inner class MainViewHolder(val itemBinding: RvItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bindData(character: Character) {


            itemBinding.name.text = character.name
            itemBinding.race.text = character.species
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