package com.example.fff.characters.detail

import android.util.Log
import com.airbnb.epoxy.CarouselModel_
import com.airbnb.epoxy.EpoxyController
import com.example.fff.R
import com.example.fff.epoxy.LoadingEpoxyModel
import com.example.fff.epoxy.ViewBindingKotlinModel
import com.example.fff.databinding.ModelCharacterDetailsDataPointBinding
import com.example.fff.databinding.ModelCharacterDetailsHeaderBinding
import com.example.fff.databinding.ModelCharacterDetailsImageBinding
import com.example.fff.databinding.ModelCharacterListTitleBinding
import com.example.fff.domain.models.Character
import com.squareup.picasso.Picasso
import com.example.fff.databinding.*
import com.example.fff.domain.models.Episode

class CharacterDetailsEpoxyController (
    private val onEpisodeClicked: (Int) -> Unit
) : EpoxyController() {

    var isLoading: Boolean = true
        set(value) {
            field = value
            if (field) {
                requestModelBuild()
            }
        }

    var character: Character? = null
        set(value) {
            field = value
            if (field != null) {
                isLoading = false
                requestModelBuild()
            }
        }

    override fun buildModels() {

        if (isLoading) {
            LoadingEpoxyModel().id("loading").addTo(this)
            return
        }

        if (character == null) {
            // todo error state
            return
        }
        // Header Model
        HeaderEpoxyModel(
            name = character!!.name,
            gender = character!!.gender,
            status = character!!.status
        ).id("header").addTo(this)

        // Image Model
        ImageEpoxyModel(
            imageUrl = character!!.image
        ).id("image").addTo(this)

        // Episode carousel list section
        Log.d("TAG11!", "buildModels1: "+character!!.episodeList.isNotEmpty())
        if (character!!.episodeList.isNotEmpty()) {
            Log.d("TAG11!", "buildModels2: "+character!!.episodeList.isNotEmpty())
            val items = character!!.episodeList.map {
                EpisodeCarouselItemEpoxyModel(it, onEpisodeClicked = { episodeId ->
                    onEpisodeClicked(episodeId)
                }).id(it.id)
            }
            CarouselModel_()
                .id("episode_carousel")
                .models(items)
                .numViewsToShowOnScreen(1.5f)
                .addTo(this)
        }

        // Data point models
        DataPointEpoxyModel(
            title = "Origin",
            description = character!!.origin.name
        ).id("data_point_1").addTo(this)

        DataPointEpoxyModel(
            title = "Species",
            description = character!!.species
        ).id("data_point_2").addTo(this)
    }

    data class HeaderEpoxyModel(
        val name: String,
        val gender: String,
        val status: String
    ) : ViewBindingKotlinModel<ModelCharacterDetailsHeaderBinding>(R.layout.model_character_details_header) {

        override fun ModelCharacterDetailsHeaderBinding.bind() {
            nameTextView.text = name
            aliveTextView.text = status

            if (gender.equals("male", true)) {
                genderImageView.setImageResource(R.mipmap.male)
            } else {
                genderImageView.setImageResource(R.mipmap.famele)
            }
        }
    }

    data class ImageEpoxyModel(
        val imageUrl: String
    ) : ViewBindingKotlinModel<ModelCharacterDetailsImageBinding>(R.layout.model_character_details_image) {

        override fun ModelCharacterDetailsImageBinding.bind() {
            Picasso.get().load(imageUrl).into(headerImageView)
        }
    }

    data class DataPointEpoxyModel(
        val title: String,
        val description: String
    ) : ViewBindingKotlinModel<ModelCharacterDetailsDataPointBinding>(R.layout.model_character_details_data_point) {

        override fun ModelCharacterDetailsDataPointBinding.bind() {
            labelTextView.text = title
            textView.text = description
        }
    }
}

    data class EpisodeCarouselItemEpoxyModel(
        val episode: Episode,
        val onEpisodeClicked: (Int) -> Unit
    ): ViewBindingKotlinModel<ModelEpisodeCarouselItemBinding>(R.layout.model_episode_carousel_item) {

        override fun ModelEpisodeCarouselItemBinding.bind() {
            episodeTextView.text = episode.getFormattedSeasonTruncated()
            episodeDetailsTextView.text = "${episode.name}\n${episode.airDate}"
            root.setOnClickListener {
                onEpisodeClicked(episode.id)
            }
        }
    }

    data class CharacterGridTitleEpoxyModel(
        val title: String
    ): ViewBindingKotlinModel<ModelCharacterListTitleBinding>(R.layout.model_character_list_title) {

        override fun ModelCharacterListTitleBinding.bind() {
            textView.text = title
        }
    }