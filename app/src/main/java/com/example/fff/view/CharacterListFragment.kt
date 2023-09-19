package com.example.fff.view

import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.airbnb.epoxy.EpoxyRecyclerView
import com.example.fff.Constants
import com.example.fff.R
import com.example.fff.view.controllers.CharacterListPagingEpoxyController
import com.example.fff.viewmodels.CharactersViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CharacterListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CharacterListFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private val epoxyController = CharacterListPagingEpoxyController(::onCharacterSelected)

    private val viewModel: CharactersViewModel by lazy {
        ViewModelProvider(this).get(CharactersViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_character_list, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CharacterListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CharacterListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

//    override fun onResume() {
//        super.onResume()
//
//        view?.postDelayed({
//            val navGraphActivity = activity as NavGraphActivity
//            navGraphActivity.navController.navigate(R.id.action_characterListFragment_to_characterDetailFragment)
//        },5_500)
//        }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    viewModel.charactersPagedListLiveData.observe(viewLifecycleOwner) { pagedList ->
        epoxyController.submitList(pagedList)
    }
    view.findViewById<EpoxyRecyclerView>(R.id.epoxyRecyclerView).setController(epoxyController)
    }
    private fun onCharacterSelected(characterId: Int) {
        val directions = CharacterListFragmentDirections.actionCharacterListFragmentToCharacterDetailFragment(
            characterId = characterId
        )

//        val intent = Intent(this, CharacterDetailActivity::class.java)
//        intent.putExtra(Constants.INTENT_EXTRA_CHARACTER_ID, characterId)
//        startActivity(intent)
//        val i = PendingIntent.getActivity(
//            this,
//            0,
//            intent,
//            PendingIntent.FLAG_MUTABLE or PendingIntent.FLAG_IMMUTABLE
//        )
//        return i
        findNavController().navigate(directions)
    }
    }