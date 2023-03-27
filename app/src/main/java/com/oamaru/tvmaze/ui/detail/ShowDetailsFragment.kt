package com.oamaru.tvmaze.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.oamaru.tvmaze.databinding.FragmentShowDetailsBinding

private val TAB_TITLES = arrayOf("Episode", "Summary")

class ShowDetailsFragment : Fragment() {

    private val showDetailsViewModel: ShowDetailsViewModel by viewModels()
    private lateinit var binding: FragmentShowDetailsBinding
    private val args: ShowDetailsFragmentArgs by navArgs()
    private lateinit var viewPager: ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentShowDetailsBinding.inflate(inflater, container, false).also {
        binding = it
        binding.viewModel = showDetailsViewModel
        showDetailsViewModel.selectedShow.set(args.selectedShow)
        showDetailsViewModel.selectedShowSeasons.set(args.showSeasons.toList())
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val stateAdapter = ViewStateAdapter(this)

        viewPager = binding.pager.apply {
            isUserInputEnabled = false
            isSaveEnabled = false
            adapter = stateAdapter
            offscreenPageLimit = 1
        }
        val tabLayout: TabLayout = binding.tabLayout
        for (i in 0..1) {
            tabLayout.addTab(tabLayout.newTab().setText(TAB_TITLES[i]))
        }

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                tabLayout.selectTab(tabLayout.getTabAt(position))
            }
        })
    }
}
