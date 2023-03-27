package com.oamaru.data.repo.schedule

import com.jakewharton.rxrelay2.BehaviorRelay
import com.oamaru.data.models.response.ShowScheduleResponse
import com.oamaru.data.service.TvMazeService
import com.oamaru.data.status.Status
import com.oamaru.data.status.subscribeWithRelay
import io.reactivex.rxjava3.schedulers.Schedulers
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ScheduleRepositoryImpl : ScheduleRepository, KoinComponent {

    private val tvMazeService: TvMazeService by inject()

    override fun getTodaySchedule(date: String): BehaviorRelay<Status<List<ShowScheduleResponse>>> {
        return BehaviorRelay.create<Status<List<ShowScheduleResponse>>>().also { relay ->
            tvMazeService.getTodaySchedule(date).subscribeOn(Schedulers.io())
                .subscribeWithRelay(relay) { it }
        }
    }
}
