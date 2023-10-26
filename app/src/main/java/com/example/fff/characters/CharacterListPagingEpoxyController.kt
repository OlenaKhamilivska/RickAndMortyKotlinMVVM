package com.example.fff.characters

import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.paging.PagedListEpoxyController
import com.example.fff.network.responce.GetCharacterByIdResponse
import com.example.fff.R
import com.example.fff.characters.detail.CharacterGridTitleEpoxyModel
import com.example.fff.epoxy.LoadingEpoxyModel
import com.example.fff.epoxy.ViewBindingKotlinModel
import com.example.fff.databinding.ModelCharacterListItemBinding
import com.squareup.picasso.Picasso
import java.util.Locale

class CharacterListPagingEpoxyController(
    private val onCharacterSelected: (Int) -> Unit
): PagedListEpoxyController<GetCharacterByIdResponse>(){

    override fun buildItemModel(
        currentPosition: Int,
        item: GetCharacterByIdResponse?
    ): EpoxyModel<*> {
        return CharacterGridItemEpoxyModel(
            characterId = item!!.id,
            imageUrl = item.image,
            name = item.name,
            onCharacterSelected = onCharacterSelected
        ).id(item.id)
    }

    override fun addModels(models: List<EpoxyModel<*>>) {
        if (models.isEmpty()) {
            LoadingEpoxyModel().id("loading").addTo(this)
            return
        }//start alfabhet A, B...Adding Paging to List
        CharacterGridTitleEpoxyModel("Main Family")
            .id("main_family_header")
            .addTo(this)
        super.addModels(models.subList(0,5))

        (models.subList(5, models.size) as List<CharacterGridItemEpoxyModel>).groupBy {
            it.name[0].toUpperCase()
        }.forEach {mapEntry->
            val character = mapEntry.key.toString().toUpperCase(Locale.US)
            CharacterGridTitleEpoxyModel(title=character)
                .id(character)
                .addTo(this)
            super.addModels(mapEntry.value)
        }
    }
}
data class CharacterGridItemEpoxyModel(
    val characterId: Int,
    val imageUrl: String,
    val name: String,
    val onCharacterSelected: (Int) -> Unit
): ViewBindingKotlinModel<ModelCharacterListItemBinding>(R.layout.model_character_list_item) {

    override fun ModelCharacterListItemBinding.bind() {
        Picasso.get().load(imageUrl).into(characterImageView)
        characterNameTextView.text = name

        root.setOnClickListener {
            onCharacterSelected(characterId)
        }
    }
//alfabet
    override fun getSpanSize(totalSpanCount: Int, position: Int, itemCount: Int): Int {
        return totalSpanCount
    }

}
