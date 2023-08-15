package com.example.fff.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fff.model.models.domain.models.Character
import com.example.fff.model.models.repositories.SharedRepository
import kotlinx.coroutines.launch

class SharedViewModel: ViewModel() {

    private val repository = SharedRepository()

    private val _characterByIdLiveData = MutableLiveData<Character?>()

    val characterByIdLiveData : LiveData<Character?> = _characterByIdLiveData

    fun refreshCharacter (characterId: Int) {
        viewModelScope.launch {
            val response = repository.getCharacterById(characterId)
            _characterByIdLiveData.postValue(response)
        }
    }

}