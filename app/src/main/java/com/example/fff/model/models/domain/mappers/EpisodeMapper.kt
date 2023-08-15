package com.example.fff.model.models.domain.mappers

import com.example.fff.model.models.domain.models.Episode
import com.example.fff.model.models.pojoResponses.episodeResponses.GetEpisodeByIdResponse

object EpisodeMapper {

    fun buildFrom(networkEpisode: GetEpisodeByIdResponse): Episode {
        return Episode(
            id = networkEpisode.id,
            name = networkEpisode.name,
            airDate = networkEpisode.air_date,
            episode = networkEpisode.episode

        )
    }
}