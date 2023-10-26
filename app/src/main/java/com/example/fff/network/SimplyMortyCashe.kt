package com.example.fff.network

import com.example.fff.domain.models.Character

object SimpleMortyCache {
    //int because characterId is int
    //com.example.fff.characters.detail.CharacterDetailFragment fetchCharacter
    //fun fetchCharacter (characterId: Int)
    val characterMap = mutableMapOf<Int, Character>()
}