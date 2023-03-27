package com.oamaru.tvmaze.ui.schedule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.oamaru.tvmaze.databinding.FragmentScheduleBinding

class ScheduleFragment : Fragment() {

    private val scheduleViewModel: ScheduleViewModel by viewModels()
    private lateinit var binding: FragmentScheduleBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentScheduleBinding.inflate(inflater, container, false).also {
        binding = it
        binding.viewModel = scheduleViewModel
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        scheduleViewModel.clickEvent.observe(viewLifecycleOwner) {
            findNavController().navigate(
                ScheduleFragmentDirections.actionScheduleFragmentToShowDetailsFragment(
                    it.show.name,
                    it.show,
                    scheduleViewModel.selectedShowSeasons.get() ?: arrayOf()
                )
            )
        }
    }
}
