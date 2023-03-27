package com.oamaru.tvmaze.ui.detail.episode

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.oamaru.data.models.response.ShowEpisodeResponse
import com.oamaru.tvmaze.databinding.EpisodeItemsBinding

class EpisodeAdapter(var episodes: List<ShowEpisodeResponse>, var listener: EpisodeListener) :
    RecyclerView.Adapter<EpisodeAdapter.EpisodeViewHolder>() {

    class EpisodeViewHolder(private val binding: EpisodeItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(episodes: ShowEpisodeResponse, listener: EpisodeListener) {
            binding.episodeImage = episodes.image?.medium
            binding.episodeName = episodes.name
            binding.episodeRuntime = "${episodes.runtime}m"

            binding.root.setOnClickListener {
                listener.onClick(episodes)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = EpisodeItemsBinding.inflate(inflater, parent, false)
        return EpisodeViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return episodes.size
    }

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        holder.bind(episodes.getOrNull(position) ?: return, listener)
    }
}
