package com.oamaru.domain.search

import com.jakewharton.rxrelay2.BehaviorRelay
import com.oamaru.data.repo.search.SearchRepository
import com.oamaru.data.status.Status
import io.reactivex.disposables.CompositeDisposable
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SearchUseCaseImpl : SearchUseCase, KoinComponent {

    private val searchRepository: SearchRepository by inject()

    override val disposables = CompositeDisposable()

    override fun execute(request: SearchUseCase.Request): BehaviorRelay<Status<SearchUseCase.Response>> {
        return searchRepository.search(request.query).convertToUseCaseResponse {
            SearchUseCase.Response(it)
        }
    }

    override fun dispose() {
        disposables.dispose()
    }
}
