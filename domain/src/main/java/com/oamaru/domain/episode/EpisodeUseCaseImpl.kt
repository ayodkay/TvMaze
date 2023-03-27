package com.oamaru.domain.episode

import com.jakewharton.rxrelay2.BehaviorRelay
import com.oamaru.data.repo.episode.ShowEpisodeRepository
import com.oamaru.data.status.Status
import io.reactivex.disposables.CompositeDisposable
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class EpisodeUseCaseImpl : EpisodeUseCase, KoinComponent {

    private val episodeRepository: ShowEpisodeRepository by inject()

    override val disposables = CompositeDisposable()

    override fun execute(request: EpisodeUseCase.Request): BehaviorRelay<Status<EpisodeUseCase.Response>> {
        return episodeRepository.getEpisodes(request.seasonId).convertToUseCaseResponse {
            EpisodeUseCase.Response(it)
        }
    }

    override fun dispose() {
        disposables.dispose()
    }
}
