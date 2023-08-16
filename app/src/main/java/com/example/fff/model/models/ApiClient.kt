package com.example.fff.model.models

import com.example.fff.model.models.pojoResponses.SimpleResponse
import com.example.fff.model.models.pojoResponses.characterResponses.GetCharacterByIdResponse
import com.example.fff.model.models.pojoResponses.characterResponses.GetCharactersPageResponse
import com.example.fff.model.models.pojoResponses.episodeResponses.GetEpisodeByIdResponse
import retrofit2.Response

class ApiClient (
private val rickAndMortyService: RickAndMortyService
) {

    // region Characters
    suspend fun getCharacterById(characterId: Int): SimpleResponse<GetCharacterByIdResponse> {
        return  safeApiCall { rickAndMortyService.getCharacterById(characterId)}
    }

    suspend fun getCharactersPage (pageId: Int): SimpleResponse<GetCharactersPageResponse> {
        return safeApiCall { rickAndMortyService.getCharacterPage(pageId) }
    }

    suspend fun getEpisodeById(episodeId: Int): SimpleResponse<GetEpisodeByIdResponse> {
        return  safeApiCall { rickAndMortyService.getEpisodeById(episodeId)}
    }

//    suspend fun getEpisodeRange (episodeRange: String): SimpleResponse<GetEpisodeByIdResponse> {
//        return safeApiCall { rickAndMortyService.getEpisodeRange(episodeRange) }
//    }
    suspend fun getEpisodeRange(episodeRange: String): SimpleResponse<List<GetEpisodeByIdResponse>> {
        return safeApiCall { rickAndMortyService.getEpisodeRange(episodeRange) }
    }


    private inline fun <T> safeApiCall(apiCall: () -> Response<T>): SimpleResponse<T> {
        return try {
            SimpleResponse.success(apiCall.invoke())
        } catch (e: Exception) {
            SimpleResponse.failure(e)
        }
    }
}