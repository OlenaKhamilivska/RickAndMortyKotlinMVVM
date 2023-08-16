package com.example.fff.model.models

import com.example.fff.model.models.pojoResponses.characterResponses.GetCharacterByIdResponse
import com.example.fff.model.models.pojoResponses.characterResponses.GetCharactersPageResponse
import com.example.fff.model.models.pojoResponses.episodeResponses.GetEpisodeByIdResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickAndMortyService {

    @GET("character/{character-id}")
    suspend fun getCharacterById(
        @Path("character-id") characterId: Int
    ): Response<GetCharacterByIdResponse>

    @GET("character")
    suspend fun getCharacterPage(
        @Query("page") pageId: Int
    ): Response<GetCharactersPageResponse>

    @GET("episode/{episode-id}")
    suspend fun getEpisodeById(
        @Path("episode-id") episodeId: Int
    ): Response<GetEpisodeByIdResponse>

    @GET("episode/{episode-range}")
    suspend fun getEpisodeRange(
        @Path("episode-id") episodeRange: String
    ): Response<GetEpisodeByIdResponse>
}