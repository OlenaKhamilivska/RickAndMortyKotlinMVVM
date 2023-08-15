package com.example.fff.view

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
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
        setContentView(R.layout.activity_main)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)



//        val mImage_IV = findViewById<AppCompatImageView>(R.id.mImage_IV)
//        val mName_TV = findViewById<AppCompatTextView>(R.id.mName_TV)



        viewModel.characterByIdLiveData.observe(this){ character->
            epoxyController.character = character
            if (character==null) {
                Toast.makeText(this@CharacterDetailActivity, "Unsuccessful network call!",
                    Toast.LENGTH_LONG).show()
                return@observe
            }
//            mName_TV.text = response.name
//            Picasso.get().load(response.image).into(mImage_IV)
//
//            if (response.gender.equals("male",true)){
//                Picasso.get().load(response.image).into(mImage_IV)
//                }
        }


//        val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
//
//        val retrofit: Retrofit = Retrofit.Builder()
////            .client(getLoggingHttpClient())
//            .baseUrl("https://rickandmortyapi.com/api/")
//            .addConverterFactory(MoshiConverterFactory.create(moshi))
//            .build()
//
//        val rickAndMortyService: RickAndMortyService = retrofit.create(RickAndMortyService::class.java)
        val id = intent.getIntExtra(Constants.INTENT_EXTRA_CHARACTER_ID, 1)
        viewModel.refreshCharacter(characterId = id)

        val epoxyRecyclerView = findViewById<EpoxyRecyclerView>(R.id.epoxyRecyclerView)
        epoxyRecyclerView.setControllerAndBuildModels(epoxyController)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            android.R.id.home->{
                finish()
                true
            }
            else ->
                super.onOptionsItemSelected(item)

        }
    }
}