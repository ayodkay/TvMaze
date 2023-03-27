package com.oamaru.domain.schedule

import com.jakewharton.rxrelay2.BehaviorRelay
import com.oamaru.data.repo.schedule.ScheduleRepository
import com.oamaru.data.status.Status
import io.reactivex.disposables.CompositeDisposable
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ScheduleUseCaseImpl : ScheduleUseCase, KoinComponent {

    private val scheduleRepository: ScheduleRepository by inject()

    override val disposables = CompositeDisposable()

    override fun execute(request: ScheduleUseCase.Request): BehaviorRelay<Status<ScheduleUseCase.Response>> {
        return scheduleRepository.getTodaySchedule(request.date).convertToUseCaseResponse {
            ScheduleUseCase.Response(it)
        }
    }

    override fun dispose() {
        disposables.dispose()
    }
}
