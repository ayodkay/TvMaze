package com.oamaru.tvmaze.ui.detail

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.oamaru.tvmaze.ui.detail.episode.EpisodeFragment
import com.oamaru.tvmaze.ui.detail.summary.SummaryFragment
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

internal class ViewStateAdapter(fragment: Fragment) :
    FragmentStateAdapter(fragment), KoinComponent {
    val context: Context by inject()
    override fun createFragment(position: Int): Fragment {
        return getFragment(position)
    }

    override fun getItemCount(): Int {
        return 2
    }

    private fun getFragment(position: Int): Fragment {
        return when (position) {
            0 -> EpisodeFragment()
            else -> SummaryFragment()
        }
    }
}
