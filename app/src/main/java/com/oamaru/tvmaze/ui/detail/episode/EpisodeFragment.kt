package com.oamaru.tvmaze.ui.detail.episode

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.oamaru.tvmaze.databinding.FragmentEpisodeBinding
import com.oamaru.tvmaze.ui.detail.ShowDetailsFragmentDirections
import com.oamaru.tvmaze.ui.detail.ShowDetailsViewModel

class EpisodeFragment : Fragment(), AdapterView.OnItemSelectedListener {

    private val showDetailsViewModel: ShowDetailsViewModel by viewModels({ requireParentFragment() })
    private val episodeViewModel: EpisodeViewModel by viewModels()

    private lateinit var binding: FragmentEpisodeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentEpisodeBinding.inflate(inflater, container, false).also {
        binding = it
        binding.episodeViewModel = episodeViewModel
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val seasons =
            showDetailsViewModel.selectedShowSeasons.get()?.map { it.number } ?: arrayListOf()

        binding.spinner.onItemSelectedListener = this

        val arrayAdapter: ArrayAdapter<Int> =
            ArrayAdapter<Int>(requireContext(), android.R.layout.simple_spinner_item, seasons)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinner.adapter = arrayAdapter

        episodeViewModel.clickEvent.observe(viewLifecycleOwner) {
            findNavController().navigate(
                ShowDetailsFragmentDirections.actionShowDetailsFragmentToEpisodeDetailsFragment(
                    it.name.orEmpty(),
                    it
                )
            )
        }
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
        episodeViewModel.currentSeasonId.set(
            showDetailsViewModel.selectedShowSeasons.get()?.getOrNull(position)?.id
        )
        episodeViewModel.getSeasonEpisodes()
    }

    override fun onNothingSelected(parent: AdapterView<*>) {}
}
