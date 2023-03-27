package com.oamaru.tvmaze.ui.detail.episode.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.oamaru.tvmaze.databinding.FragmentEpisodeDetailBinding

class EpisodeDetailFragment : Fragment() {

    private lateinit var binding: FragmentEpisodeDetailBinding
    private val args: EpisodeDetailFragmentArgs by navArgs()
    private val episodeDetailViewModel: EpisodeDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentEpisodeDetailBinding.inflate(inflater, container, false).also {
        binding = it
        binding.viewModel = episodeDetailViewModel
        episodeDetailViewModel.selectedEpisode.set(args.episode)
    }.root
}
