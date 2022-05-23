package com.example.rickandmortyretrofitnew

import com.example.rickandmortyretrofitnew.network.ApiService

class Repository(private val apiService: ApiService) {
    fun getCharacters(page:String) = apiService.fetchCharacters(page)
}