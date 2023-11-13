package com.example.fff.network

import com.example.fff.network.responce.GetCharacterByIdResponse
import com.example.fff.network.responce.GetCharactersPageResponse
import com.example.fff.network.responce.GetEpisodeByIdResponse
import com.example.fff.network.responce.GetEpisodesPageResponse
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

    @GET("character")
    suspend fun getCharacterPage(
        @Query("name") characterName: String,
        @Query("page") pageId: Int
    ): Response<GetCharactersPageResponse>

    @GET("episode/{episode-id}")
    suspend fun getEpisodeById(
        @Path("episode-id") episodeId: Int
    ): Response<GetEpisodeByIdResponse>

//    @GET("episode/{episode-range}")
//    suspend fun getEpisodeRange(
//        @Path("episode-id") episodeRange: String
//    ): Response<GetEpisodeByIdResponse>

    @GET("episode/{episode-range}")
    suspend fun getEpisodeRange(
        @Path("episode-range") episodeRange: String
    ): Response<List<GetEpisodeByIdResponse>>

    @GET("episode/")
    suspend fun getEpisodesPage(
        @Query("page") pageIndex: Int
    ): Response<GetEpisodesPageResponse>

    @GET("character/{list}")
    suspend fun getMultipleCharacters(
        @Path("list") characterList: List<String>
    ): Response<List<GetCharacterByIdResponse>>
}