package com.example.fff.characters

import com.example.fff.network.SimpleMortyCache
import com.example.fff.network.NetworkLayer
import com.example.fff.domain.mappers.CharacterMapper
import com.example.fff.domain.models.Character
import com.example.fff.network.responce.GetCharacterByIdResponse
import com.example.fff.network.responce.GetCharactersPageResponse
import com.example.fff.network.responce.GetEpisodeByIdResponse

class CharactersRepository {

    suspend fun getCharacterList(pageIndex: Int): List<GetCharacterByIdResponse> {
        return emptyList()
    }

    suspend fun getCharactersPage(pageIndex: Int): GetCharactersPageResponse? {
        val request = NetworkLayer.apiClient.getCharactersPage(pageIndex)

        if (request.failed || !request.isSuccessful) {
            return null
        }
        return request.body
    }

    suspend fun getCharacterById(characterId: Int): Character? {
        // Check the cache for our character
        val cachedCharacter = SimpleMortyCache.characterMap[characterId]
        if (cachedCharacter != null) {
            return cachedCharacter
        }

        val request = NetworkLayer.apiClient.getCharacterById(characterId)

        if (request.failed || !request.isSuccessful) {
            return null
        }

        val networkEpisodes = getEpisodesFromCharacterResponse(request.body)
        val character = CharacterMapper.buildFrom(
            response = request.body,
            episodes = networkEpisodes
        )

        // Update cache & return value
        SimpleMortyCache.characterMap[characterId] = character
        return character
    }

    private suspend fun getEpisodesFromCharacterResponse(
        characterResponse: GetCharacterByIdResponse
    ): List<GetEpisodeByIdResponse> {
        val episodeRange = characterResponse.episode.map {
            it.substring(startIndex = it.lastIndexOf("/") + 1)
        }.toString()

        val request = NetworkLayer.apiClient.getEpisodeRange(episodeRange)

        if (request.failed || !request.isSuccessful) {
            return emptyList()
        }

        return request.body
    }
}
