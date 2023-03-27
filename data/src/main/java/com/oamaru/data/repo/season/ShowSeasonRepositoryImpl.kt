package com.oamaru.data.repo.season

import com.jakewharton.rxrelay2.BehaviorRelay
import com.oamaru.data.models.response.ShowSeasonsResponse
import com.oamaru.data.service.TvMazeService
import com.oamaru.data.status.Status
import com.oamaru.data.status.subscribeWithRelay
import io.reactivex.rxjava3.schedulers.Schedulers
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ShowSeasonRepositoryImpl : ShowSeasonRepository, KoinComponent {

    private val tvMazeService: TvMazeService by inject()

    override fun getSeason(showId: Int): BehaviorRelay<Status<List<ShowSeasonsResponse>>> =
        BehaviorRelay.create<Status<List<ShowSeasonsResponse>>>().also { relay ->
            tvMazeService.getShowSeasons(showId).subscribeOn(Schedulers.io())
                .subscribeWithRelay(relay) { it }
        }
}
