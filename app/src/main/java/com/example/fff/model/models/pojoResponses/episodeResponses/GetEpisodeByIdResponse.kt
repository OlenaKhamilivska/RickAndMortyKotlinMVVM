package com.example.fff.model.models.pojoResponses.episodeResponses

data class GetEpisodeByIdResponse(
    val air_date: String,
    val characters: List<String>,
    val created: String,
    val episode: String,
    val id: Int,
    val name: String,
    val url: String
)