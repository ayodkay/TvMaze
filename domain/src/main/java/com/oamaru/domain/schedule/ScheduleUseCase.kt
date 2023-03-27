package com.oamaru.domain.schedule

import com.oamaru.data.models.response.ShowScheduleResponse
import com.oamaru.domain.BaseUseCase
import org.koin.core.component.KoinComponent

interface ScheduleUseCase :
    BaseUseCase<ScheduleUseCase.Request, ScheduleUseCase.Response>,
    KoinComponent {
    class Request(val date: String) : BaseUseCase.Request()
    class Response(val schedule: List<ShowScheduleResponse>) : BaseUseCase.Response()
}
