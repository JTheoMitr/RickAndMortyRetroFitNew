package com.example.rickandmortyretrofitnew.characters

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyretrofitnew.Repository
import com.example.rickandmortyretrofitnew.ScreenState
import com.example.rickandmortyretrofitnew.network.ApiClient
import com.example.rickandmortyretrofitnew.network.Character
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class CharactersViewModel(private val repository: Repository = Repository(ApiClient.apiService)) : ViewModel() {

    private var _charactersLiveData = MutableLiveData<ScreenState<List<Character>?>>()
    val characterLiveData : LiveData<ScreenState<List<Character>?>>
    get() = _charactersLiveData

    init {
        fetchCharacters()
    }

    private fun fetchCharacters() {

        _charactersLiveData.postValue(ScreenState.Loading(null))
        viewModelScope.launch(Dispatchers.IO) {
            Log.d("CharactersViewModel", Thread.currentThread().name)
            try {
                val client = repository.getCharacters("1")
                _charactersLiveData.postValue(ScreenState.Success(client.result))
            } catch (e: Exception) {
                _charactersLiveData.postValue(ScreenState.Error(e.message.toString(), null))
            }
        }
    }


}