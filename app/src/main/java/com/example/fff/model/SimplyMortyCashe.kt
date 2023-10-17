package com.example.fff.model

import com.example.fff.model.models.domain.models.Character

object SimpleMortyCache {
    //int because characterId is int
    //com.example.fff.view.CharacterDetailFragment fetchCharacter
    //fun fetchCharacter (characterId: Int)
    val characterMap = mutableMapOf<Int, Character>()
}