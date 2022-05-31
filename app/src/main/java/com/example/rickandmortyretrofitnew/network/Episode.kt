package com.example.rickandmortyretrofitnew.network

import com.squareup.moshi.Json

data class Episode(
    @Json(name = "name")
    val name: String,
    @Json(name = "air_date")
    val airDate: String,
    @Json(name = "episode")
    val episode: String,
    @Json(name = "characters")
    val characters: List<String>
)



data class EpisodeResponse(
    @Json(name = "results")
    val result: List<Episode>
)