package com.oamaru.domain.episode

import com.oamaru.data.models.response.ShowEpisodeResponse
import com.oamaru.domain.BaseUseCase
import org.koin.core.component.KoinComponent

interface EpisodeUseCase :
    BaseUseCase<EpisodeUseCase.Request, EpisodeUseCase.Response>,
    KoinComponent {
    class Request(val seasonId: Int) : BaseUseCase.Request()
    class Response(val episode: List<ShowEpisodeResponse>) : BaseUseCase.Response()
}
