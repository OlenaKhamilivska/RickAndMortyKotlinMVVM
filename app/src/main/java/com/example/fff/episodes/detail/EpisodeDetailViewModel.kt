package com.example.fff.episodes.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fff.domain.models.Episode
import kotlinx.coroutines.launch

class EpisodeDetailViewModel : ViewModel() {

    private val repository = EpisodeRepository()
    private var _episodeLiveData = MutableLiveData<Episode?>()
    val episodeLiveData: LiveData<Episode?> = _episodeLiveData

    fun fetchEpisode(episodeId: Int) {
        viewModelScope.launch {
            val episode = repository.getEpisodeById(episodeId)
            Log.d("mmmTAG", "fetchEpisode: ${episode}")
            _episodeLiveData.postValue(episode)
        }
    }
}