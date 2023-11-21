package com.example.fff.episodes.detail

import com.example.fff.domain.models.Episode

sealed class EpisodesUiModel {
    class Item(val episode: Episode): EpisodesUiModel()
    class Header(val text: String): EpisodesUiModel()
}