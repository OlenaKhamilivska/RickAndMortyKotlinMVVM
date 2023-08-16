package com.example.fff.view

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.epoxy.EpoxyRecyclerView
import com.example.fff.Constants
import com.example.fff.R
import com.example.fff.view.controllers.CharacterListPagingEpoxyController
import com.example.fff.viewmodels.CharactersViewModel

class CharacterListActivity: AppCompatActivity() {

    private val epoxyController = CharacterListPagingEpoxyController(::onCharacterSelected)

    private val viewModel: CharactersViewModel by lazy {
        ViewModelProvider(this).get(CharactersViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_list)

        viewModel.charactersPagedListLiveData.observe(this){pagedList->
            epoxyController.submitList(pagedList)
        }
       // findViewById<EpoxyRecyclerView>(R.id.epoxyRecyclerView).setController(epoxyController)

        val epoxyRecyclerView = findViewById<EpoxyRecyclerView>(R.id.epoxyRecyclerView)
        epoxyRecyclerView.layoutManager = GridLayoutManager(this,2)


        epoxyRecyclerView.apply {

            layoutManager = GridLayoutManager(applicationContext, 2)
            epoxyRecyclerView.setControllerAndBuildModels(epoxyController)
        }




    }
    private fun onCharacterSelected(characterId: Int) {
        val intent= Intent (this, CharacterDetailActivity::class.java)
        intent.putExtra(Constants.INTENT_EXTRA_CHARACTER_ID, characterId)
        startActivity(intent)
    }
}