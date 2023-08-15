package com.example.fff.model.models.repositories

import com.example.fff.model.models.NetworkLayer
import com.example.fff.model.models.domain.mappers.CharacterMapper
import com.example.fff.model.models.domain.models.Character
import com.example.fff.model.models.pojoResponses.characterResponses.GetCharacterByIdResponse
import com.example.fff.model.models.pojoResponses.episodeResponses.GetEpisodeByIdResponse

class SharedRepository {

    suspend fun getCharacterById(characterId: Int): Character? {
        val request = NetworkLayer.apiClient.getCharacterById(characterId)

        if (request.failed || !request.isSuccessful) {
            return null
        }
        val networkEpisode = getEpisodesFromCharacterResponse(request.body)
        return CharacterMapper.buildFrom(
            response = request.body,
            episodes = networkEpisode
        )
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
    return emptyList()
}
}

