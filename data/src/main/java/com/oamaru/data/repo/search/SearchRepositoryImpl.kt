package com.oamaru.data.repo.search

import com.jakewharton.rxrelay2.BehaviorRelay
import com.oamaru.data.models.response.SearchResponse
import com.oamaru.data.service.TvMazeService
import com.oamaru.data.status.Status
import com.oamaru.data.status.subscribeWithRelay
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SearchRepositoryImpl : SearchRepository, KoinComponent {

    private val tvMazeService: TvMazeService by inject()

    override fun search(query: String): BehaviorRelay<Status<List<SearchResponse>>> =
        BehaviorRelay.create<Status<List<SearchResponse>>>().also { relay ->
            tvMazeService.search(query).subscribeWithRelay(relay) { it }
        }
}
