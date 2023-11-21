package com.example.fff.network.responce
class GetEpisodesPageResponse (
    val info: PageInfo = PageInfo(),
    val results: List<GetEpisodeByIdResponse> = emptyList()
)