package com.oamaru.data.repo.search

import com.jakewharton.rxrelay2.BehaviorRelay
import com.oamaru.data.models.response.SearchResponse
import com.oamaru.data.status.Status

interface SearchRepository {
    fun search(query: String): BehaviorRelay<Status<List<SearchResponse>>>
}
