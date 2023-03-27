package com.oamaru.tvmaze.ui.detail.episode

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.oamaru.data.models.response.ShowEpisodeResponse

@BindingAdapter(value = ["android:episodes", "android:episodesListener"])
fun RecyclerView.episodes(episodes: List<ShowEpisodeResponse>?, episodesListener: EpisodeListener) {
    if (episodes != null) {
        if (adapter == null) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = EpisodeAdapter(episodes, episodesListener)
        } else {
            (adapter as EpisodeAdapter).apply {
                this.episodes = episodes
                this.listener = episodesListener
                notifyDataSetChanged()
            }
        }
    }
}
