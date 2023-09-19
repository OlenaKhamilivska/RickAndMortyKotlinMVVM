package com.example.fff.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.airbnb.epoxy.EpoxyRecyclerView
import com.example.fff.Constants
import com.example.fff.R
import com.example.fff.view.controllers.CharacterDetailsEpoxyController
import com.example.fff.viewmodels.SharedViewModel


//private lateinit var mName_TV : TextView

class CharacterDetailActivity : AppCompatActivity() {

    val viewModel : SharedViewModel by lazy {
        ViewModelProvider(this).get(SharedViewModel::class.java)
    }

    private val epoxyController = CharacterDetailsEpoxyController()
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_details)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewModel.characterByIdLiveData.observe(this){ character->
            epoxyController.character = character
            if (character==null) {
                Toast.makeText(this@CharacterDetailActivity, "Unsuccessful network call!",
                    Toast.LENGTH_LONG).show()
                return@observe
            }
        }
        val id = intent.getIntExtra(Constants.INTENT_EXTRA_CHARACTER_ID, 1)

        viewModel.refreshCharacter(characterId = id)

        val epoxyRecyclerView = findViewById<EpoxyRecyclerView>(R.id.epoxyRecyclerView)

        epoxyRecyclerView.setControllerAndBuildModels(epoxyController)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId){
            android.R.id.home->{
                finish()
                true
            }
            else ->
                super.onOptionsItemSelected(item)

        }
    }