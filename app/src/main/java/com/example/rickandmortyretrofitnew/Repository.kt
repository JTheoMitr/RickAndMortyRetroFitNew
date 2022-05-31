package com.example.rickandmortyretrofitnew

import com.example.rickandmortyretrofitnew.network.ApiService

class Repository(private val apiService: ApiService) {
    suspend fun getCharacters(page:String) = apiService.fetchCharacters(page)

    suspend fun getEpisodes(page:String) = apiService.fetchEpisodes(page)
}