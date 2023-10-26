package com.example.fff.characters.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fff.characters.CharactersRepository
import com.example.fff.domain.models.Character
import kotlinx.coroutines.launch

class CharacterDetailViewModel: ViewModel() {

    private val repository = CharactersRepository()

    private val _characterByIdLiveData = MutableLiveData<Character?>()

    val characterByIdLiveData : LiveData<Character?> = _characterByIdLiveData
//fetch - вибірка
    fun fetchCharacter (characterId: Int) {
    //otherwise we need to make a network call for the character
        viewModelScope.launch {
        val character = repository.getCharacterById(characterId)
        _characterByIdLiveData.postValue(character)

//        //check the cache for our character
//        val cachedCharacter = SimpleMortyCache.characterMap[characterId]
//        if (cachedCharacter != null){
//            _characterByIdLiveData.postValue(cachedCharacter)
//            return
//        }

//update cache if non-null char received
//            response?.let {
//                SimpleMortyCache.characterMap[characterId] = it
//            }
        }
    }
}