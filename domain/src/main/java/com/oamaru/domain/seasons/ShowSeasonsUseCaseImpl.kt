package com.oamaru.domain.seasons

import com.jakewharton.rxrelay2.BehaviorRelay
import com.oamaru.data.repo.season.ShowSeasonRepository
import com.oamaru.data.status.Status
import io.reactivex.disposables.CompositeDisposable
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ShowSeasonsUseCaseImpl : ShowSeasonsUseCase, KoinComponent {

    private val showSeasonRepository: ShowSeasonRepository by inject()

    override val disposables = CompositeDisposable()

    override fun execute(request: ShowSeasonsUseCase.Request): BehaviorRelay<Status<ShowSeasonsUseCase.Response>> {
        return showSeasonRepository.getSeason(request.showId).convertToUseCaseResponse {
            ShowSeasonsUseCase.Response(it)
        }
    }

    override fun dispose() {
        disposables.dispose()
    }
}
