package com.example.fff.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.airbnb.epoxy.EpoxyRecyclerView
import com.example.fff.Constants
import com.example.fff.R
import com.example.fff.view.controllers.CharacterDetailsEpoxyController
import com.example.fff.viewmodels.SharedViewModel

class CharacterDetailFragment : Fragment() {

   private val viewModel : SharedViewModel by lazy {
        ViewModelProvider(this).get(SharedViewModel::class.java)
    }

   private val epoxyController = CharacterDetailsEpoxyController()
    private val safeArgs: CharacterDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_character_detail, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        viewModel.characterByIdLiveData.observe(viewLifecycleOwner){ character->
            epoxyController.character = character
            if (character==null) {
                Toast.makeText(requireActivity(),
                    "Unsuccessful network call!",
                    Toast.LENGTH_LONG).show()
                return@observe
            }
        }
        viewModel.refreshCharacter(characterId = safeArgs.characterId)

        val epoxyRecyclerView = view.findViewById<EpoxyRecyclerView>(R.id.epoxyRecyclerView)

        epoxyRecyclerView.setControllerAndBuildModels(epoxyController)
    }
}