package com.oamaru.tvmaze.ui.detail.summary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.oamaru.tvmaze.databinding.FragmentSummaryBinding
import com.oamaru.tvmaze.ui.detail.ShowDetailsViewModel

class SummaryFragment : Fragment() {

    private val showDetailsViewModel: ShowDetailsViewModel by viewModels({ requireParentFragment() })

    private lateinit var binding: FragmentSummaryBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentSummaryBinding.inflate(inflater, container, false).also {
        binding = it
        binding.viewModel = showDetailsViewModel
    }.root
}
