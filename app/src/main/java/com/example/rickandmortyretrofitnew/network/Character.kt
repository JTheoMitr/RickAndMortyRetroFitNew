package com.example.rickandmortyretrofitnew.network

import com.squareup.moshi.Json

data class Character(
    @Json(name = "name")
    val name: String,
    @Json(name = "image")
    val image: String,
    @Json(name = "species")
    val species: String,
    @Json(name = "origin")
    val origin: Origin,
    @Json(name = "episode")
    val episode: List<String>
)

data class Origin(
    @Json(name = "name")
    val name: String,
    @Json(name = "url")
    val url: String
)


data class CharacterResponse(
    @Json(name = "results")
    val result: List<Character>
)
