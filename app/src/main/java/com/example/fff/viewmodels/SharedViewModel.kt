package com.example.fff.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fff.model.SimpleMortyCache
import com.example.fff.model.models.domain.models.Character
import com.example.fff.model.models.repositories.SharedRepository
import kotlinx.coroutines.launch

class SharedViewModel: ViewModel() {

    private val repository = SharedRepository()

    private val _characterByIdLiveData = MutableLiveData<Character?>()

    val characterByIdLiveData : LiveData<Character?> = _characterByIdLiveData

    fun fetchCharacter (characterId: Int) {

        //check the cache for our character
        val cachedCharacter = SimpleMortyCache.characterMap[characterId]
        if (cachedCharacter != null){
            _characterByIdLiveData.postValue(cachedCharacter)
            return
        }
        //otherwise we need to make a network call for the character
        viewModelScope.launch {
            val response = repository.getCharacterById(characterId)
            _characterByIdLiveData.postValue(response)
//update cache if non-null char received
            response?.let {
                SimpleMortyCache.characterMap[characterId] = it
            }
        }
    }
}