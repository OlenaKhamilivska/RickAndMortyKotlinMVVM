package com.example.fff.episodes.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.PagingData
import com.example.fff.BaseFragment
import com.example.fff.NavGraphDirections
import com.example.fff.R
import com.example.fff.databinding.FragmentEpisodeListBinding
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class EpisodeListFragment : BaseFragment(R.layout.fragment_episode_list) {

    private var _binding: FragmentEpisodeListBinding? = null
    private val binding get () = _binding!!
    private val viewModel: EpisodesViewModel by viewModels()

    @ObsoleteCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentEpisodeListBinding.bind(view)

        val epoxyController = EpisodeListEpoxyController {episodeClickedId ->
            val navDirections =
              NavGraphDirections.actionGlobalToEpisodeDetailBottomSheetFragment(
                  episodeId = episodeClickedId
              )
            findNavController().navigate(navDirections)
        }

        lifecycleScope.launch {
            viewModel.flow.collectLatest { pagingData: PagingData<EpisodesUiModel> ->
                epoxyController.submitData(pagingData)
            }
        }
        binding.epoxyRecyclerView.setController(epoxyController)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}