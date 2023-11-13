package com.example.fff.characters.search

import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.paging3.PagingDataEpoxyController
import com.example.fff.R
import com.example.fff.databinding.ModelCharacterListItemBinding
import com.example.fff.domain.models.Character
import com.example.fff.epoxy.LoadingEpoxyModel
import com.example.fff.epoxy.ViewBindingKotlinModel
import com.squareup.picasso.Picasso
import kotlinx.coroutines.ObsoleteCoroutinesApi

@ObsoleteCoroutinesApi
class CharacterSearchEpoxyController(
    private val onCharacterSelected: (Int) -> Unit
) : PagingDataEpoxyController<com.example.fff.domain.models.Character>() {

    var localException: CharacterSearchPagingSource.LocalException? = null
        set(value) {
            field = value
            if (localException != null) {
                requestForcedModelBuild()
            }
        }

    override fun buildItemModel(currentPosition: Int, item: Character?): EpoxyModel<*> {
        return CharacterGridItemEpoxyModel(
            characterId = item!!.id,
            imageUrl = item.image,
            name = item.name,
            onCharacterSelected = { characterId ->
                onCharacterSelected(characterId)
            }
        ).id(item.id)
    }

    override fun addModels(models: List<EpoxyModel<*>>) {
        localException?.let {
// TODO:
        }
        if (models.isEmpty()) {
            LoadingEpoxyModel().id("loading").addTo(this)
            return
        }

        super.addModels(models)
    }

    data class CharacterGridItemEpoxyModel(
        val characterId: Int,
        val imageUrl: String,
        val name: String,
        val onCharacterSelected: (Int) -> Unit
    ) : ViewBindingKotlinModel<ModelCharacterListItemBinding>(R.layout.model_character_list_item) {

        override fun ModelCharacterListItemBinding.bind() {
            Picasso.get().load(imageUrl).into(characterImageView)
            characterNameTextView.text = name

            root.setOnClickListener {
                onCharacterSelected(characterId)
            }
        }
    }
}
//
//    data class LocalExceptionErrorStateEpoxyModel(
//        val localException: CharacterSearchPagingSource.LocalException
//    ) : ViewBindingKotlinModel<ModelLocalExceptionErrorStateBinding>(R.layout.model_local_exception_error_state) {
//
//        override fun ModelLocalExceptionErrorStateBinding.bind() {
//            titleTextView.text = localException.title
//            descriptionTextView.text = localException.description
//        }
//
//        override fun getSpanSize(totalSpanCount: Int, position: Int, itemCount: Int): Int {
//            return totalSpanCount
//        }
//    }
