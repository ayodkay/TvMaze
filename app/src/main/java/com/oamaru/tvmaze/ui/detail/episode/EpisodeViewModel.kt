package com.oamaru.tvmaze.ui.detail.episode

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.oamaru.data.models.response.ShowEpisodeResponse
import com.oamaru.data.status.loading
import com.oamaru.data.status.onError
import com.oamaru.data.status.onSuccess
import com.oamaru.domain.episode.EpisodeUseCase
import com.oamaru.tvmaze.Event
import com.oamaru.tvmaze.trigger
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class EpisodeViewModel : ViewModel(), KoinComponent, EpisodeListener {

    private val episodeUseCase: EpisodeUseCase by inject()

    val listener = this

    val loading = ObservableField<Boolean>()
    val currentSeasonId = ObservableField<Int>()
    val episodes = ObservableField<List<ShowEpisodeResponse>>()

    val clickEvent = Event<ShowEpisodeResponse>()

    fun getSeasonEpisodes() {
        currentSeasonId.get()?.let {
            episodeUseCase.execute(EpisodeUseCase.Request(it)).subscribe { status ->
                status.onSuccess { response ->
                    episodes.set(response.episode)
                    loading.set(false)
                }.loading {
                    loading.set(true)
                }.onError {
                    loading.set(false)
                }
            }
        }
    }

    override fun onClick(episodeResponse: ShowEpisodeResponse) {
        clickEvent.trigger(episodeResponse)
    }
}

interface EpisodeListener {
    fun onClick(episodeResponse: ShowEpisodeResponse)
}
