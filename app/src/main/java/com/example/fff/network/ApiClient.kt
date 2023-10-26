package com.example.fff.network

import com.example.fff.network.responce.GetCharacterByIdResponse
import com.example.fff.network.responce.GetCharactersPageResponse
import com.example.fff.network.responce.GetEpisodeByIdResponse
import com.example.fff.network.responce.GetEpisodesPageResponse
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

    suspend fun getEpisodesPage(pageIndex: Int): SimpleResponse<GetEpisodesPageResponse> {
        return safeApiCall { rickAndMortyService.getEpisodesPage(pageIndex) }
    }


    private inline fun <T> safeApiCall(apiCall: () -> Response<T>): SimpleResponse<T> {
        return try {
            SimpleResponse.success(apiCall.invoke())
        } catch (e: Exception) {
            SimpleResponse.failure(e)
        }
    }
}