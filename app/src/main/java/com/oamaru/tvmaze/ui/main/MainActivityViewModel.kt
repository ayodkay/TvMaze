package com.oamaru.tvmaze

import android.annotation.SuppressLint
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.oamaru.data.models.response.SearchResponse
import com.oamaru.data.models.response.ShowSeasonsResponse
import com.oamaru.data.status.loading
import com.oamaru.data.status.onError
import com.oamaru.data.status.onSuccess
import com.oamaru.domain.search.SearchUseCase
import com.oamaru.domain.seasons.ShowSeasonsUseCase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MainActivityViewModel : ViewModel(), KoinComponent, SearchListener {

    private val searchUseCase: SearchUseCase by inject()
    private val showSeasonsUseCase: ShowSeasonsUseCase by inject()

    val listener = this
    val loading = ObservableField<Boolean>()
    val searchQuery = ObservableField("")
    val search = ObservableField<List<SearchResponse>>()
    val selectedShowSeasons = ObservableField<Array<ShowSeasonsResponse>>()

    val clickEvent = Event<SearchResponse>()

    fun searchShow() {
        searchQuery.get()?.let {
            searchUseCase.execute(SearchUseCase.Request(it)).subscribe { status ->
                status.onSuccess { response ->
                    loading.set(false)
                    search.set(response.result)
                }.loading {
                    loading.set(true)
                }.onError {
                    loading.set(false)
                }
            }
        }
    }

    @SuppressLint("CheckResult")
    override fun onClick(search: SearchResponse) {
        showSeasonsUseCase.execute(ShowSeasonsUseCase.Request(search.show.id)).subscribe { status ->
            status.onSuccess { response ->
                loading.set(false)
                selectedShowSeasons.set(response.seasons.toTypedArray())
                clickEvent.trigger(search)
            }.loading {
                loading.set(true)
            }.onError {
                loading.set(false)
            }
        }
    }

    override fun onCleared() {
        searchUseCase.dispose()
        showSeasonsUseCase.dispose()
        super.onCleared()
    }
}

interface SearchListener {
    fun onClick(search: SearchResponse)
}
