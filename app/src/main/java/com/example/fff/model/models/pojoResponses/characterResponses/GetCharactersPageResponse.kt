package com.example.fff.model.models.pojoResponses.characterResponses

data class GetCharactersPageResponse(
    val info: Info,
    val results: List<GetCharacterByIdResponse> = emptyList()
)

data class Info(
    val count: Int = 0,
    val next: String? = null,
    val pages: Int = 0,
    val prev: String? =null
)