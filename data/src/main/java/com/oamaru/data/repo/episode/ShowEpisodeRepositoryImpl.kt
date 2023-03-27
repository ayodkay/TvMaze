package com.oamaru.data.repo.episode

import com.jakewharton.rxrelay2.BehaviorRelay
import com.oamaru.data.models.response.ShowEpisodeResponse
import com.oamaru.data.service.TvMazeService
import com.oamaru.data.status.Status
import com.oamaru.data.status.subscribeWithRelay
import io.reactivex.rxjava3.schedulers.Schedulers
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ShowEpisodeRepositoryImpl : ShowEpisodeRepository, KoinComponent {

    private val tvMazeService: TvMazeService by inject()

    override fun getEpisodes(seasonId: Int): BehaviorRelay<Status<List<ShowEpisodeResponse>>> {
        return BehaviorRelay.create<Status<List<ShowEpisodeResponse>>>().also { relay ->
            tvMazeService.getShowEpisodes(seasonId).subscribeOn(Schedulers.io())
                .subscribeWithRelay(relay) { it }
        }
    }
}
