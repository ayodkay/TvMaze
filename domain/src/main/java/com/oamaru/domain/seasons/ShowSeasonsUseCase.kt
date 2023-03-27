package com.oamaru.domain.seasons

import com.oamaru.data.models.response.ShowSeasonsResponse
import com.oamaru.domain.BaseUseCase
import org.koin.core.component.KoinComponent

interface ShowSeasonsUseCase :
    BaseUseCase<ShowSeasonsUseCase.Request, ShowSeasonsUseCase.Response>,
    KoinComponent {
    class Request(val showId: Int) : BaseUseCase.Request()
    class Response(val seasons: List<ShowSeasonsResponse>) : BaseUseCase.Response()
}
