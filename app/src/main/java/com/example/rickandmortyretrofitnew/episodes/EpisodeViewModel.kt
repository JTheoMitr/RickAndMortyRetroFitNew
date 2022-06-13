package com.example.rickandmortyretrofitnew.episodes

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyretrofitnew.Repository
import com.example.rickandmortyretrofitnew.ScreenState
import com.example.rickandmortyretrofitnew.network.ApiClient
import com.example.rickandmortyretrofitnew.network.Episode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EpisodeViewModel(private val repository: Repository = Repository(ApiClient.apiService)) : ViewModel() {

    private var _episodesLiveData = MutableLiveData<ScreenState<List<Episode>?>>()
    val episodesLiveData : LiveData<ScreenState<List<Episode>?>>
        get() = _episodesLiveData

    init {

        fetchEpisodes()
    }

    private fun fetchEpisodes() {

        _episodesLiveData.postValue(ScreenState.Loading(null))
        viewModelScope.launch(Dispatchers.IO) {
            Log.d("EpisodeViewModel", Thread.currentThread().name)
            try {
                val client = repository.getEpisodes("1")
                _episodesLiveData.postValue(ScreenState.Success(client.result))
            } catch (e: Exception) {
                _episodesLiveData.postValue(ScreenState.Error(e.message.toString(), null))
            }
        }
    }

}