package com.oamaru.tvmaze.ui.detail.episode.details

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.oamaru.data.models.response.ShowEpisodeResponse

class EpisodeDetailViewModel : ViewModel() {
    val selectedEpisode = ObservableField<ShowEpisodeResponse>()
}
