package com.example.fff.characters.detail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.airbnb.epoxy.EpoxyRecyclerView
import com.example.fff.R

class CharacterDetailFragment : Fragment() {

   private val viewModel : CharacterDetailViewModel by lazy {
        ViewModelProvider(this).get(CharacterDetailViewModel::class.java)
    }

   private val epoxyController = CharacterDetailsEpoxyController{ episodeClickedId ->
//       val navDirections = NavGraphDirections.actionGlobalToEpisodeDetailBottomSheetFragment(
//           episodeId = episodeClickedId
//       )
//       findNavController().navigate(navDirections)
   }
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
                findNavController().navigateUp()
                return@observe
            }
            Log.d("bTAG", "onViewCreated: ")
        }
        viewModel.fetchCharacter(characterId = safeArgs.characterId)

        val epoxyRecyclerView = view.findViewById<EpoxyRecyclerView>(R.id.epoxyRecyclerView)
        epoxyRecyclerView.setControllerAndBuildModels(epoxyController)
    }
}