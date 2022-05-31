package com.example.rickandmortyretrofitnew


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.example.rickandmortyretrofitnew.databinding.RvEpisodeBinding
import com.example.rickandmortyretrofitnew.network.Episode

class EpisodesAdapter(private val episodesList: List<Episode>) : RecyclerView.Adapter<EpisodesAdapter.MainViewHolder>() {

    inner class MainViewHolder(private val episodeBinding: RvEpisodeBinding) : RecyclerView.ViewHolder(episodeBinding.root) {
        fun bindData(episode: Episode) {


            episodeBinding.episodeName.text = episode.name
            episodeBinding.episodeNumber.text = episode.episode
            episodeBinding.episodeAirDate.text = episode.airDate
//            itemBinding.image.load(character.image) {
//                transformations(CircleCropTransformation())
//            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            RvEpisodeBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bindData(episodesList[position])
    }

    override fun getItemCount(): Int {
        return episodesList.size
    }

}