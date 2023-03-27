package com.oamaru.domain.search

import com.oamaru.data.models.response.SearchResponse
import com.oamaru.domain.BaseUseCase
import org.koin.core.component.KoinComponent

interface SearchUseCase :
    BaseUseCase<SearchUseCase.Request, SearchUseCase.Response>,
    KoinComponent {
    class Request(val query: String) : BaseUseCase.Request()
    class Response(val result: List<SearchResponse>) : BaseUseCase.Response()
}
