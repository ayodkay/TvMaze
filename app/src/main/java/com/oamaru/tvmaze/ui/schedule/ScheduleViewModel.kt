package com.oamaru.tvmaze.ui.schedule

import android.annotation.SuppressLint
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.oamaru.data.models.response.ShowScheduleResponse
import com.oamaru.data.models.response.ShowSeasonsResponse
import com.oamaru.data.status.loading
import com.oamaru.data.status.onError
import com.oamaru.data.status.onSuccess
import com.oamaru.domain.schedule.ScheduleUseCase
import com.oamaru.domain.seasons.ShowSeasonsUseCase
import com.oamaru.tvmaze.Event
import com.oamaru.tvmaze.trigger
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.threeten.bp.LocalDateTime

@SuppressLint("CheckResult")
class ScheduleViewModel : ViewModel(), ScheduleListeners, KoinComponent {
    val listeners = this
    private val scheduleUseCase: ScheduleUseCase by inject()
    private val showSeasonsUseCase: ShowSeasonsUseCase by inject()
    val schedule = ObservableField<List<ShowScheduleResponse>>()
    val loading = ObservableField(true)
    val selectedShowSeasons = ObservableField<Array<ShowSeasonsResponse>>()
    val clickEvent = Event<ShowScheduleResponse>()

    init {
        val currentDate = LocalDateTime.now().toLocalDate().toString()
        scheduleUseCase.execute(ScheduleUseCase.Request(currentDate)).subscribe { status ->
            status.onSuccess { response ->
                schedule.set(response.schedule)
                loading.set(false)
            }.loading {
                loading.set(true)
            }.onError {
                loading.set(false)
            }
        }
    }

    override fun onClick(schedule: ShowScheduleResponse) {
        showSeasonsUseCase.execute(ShowSeasonsUseCase.Request(schedule.show.id)).subscribe { status ->
            status.onSuccess { response ->
                loading.set(false)
                selectedShowSeasons.set(response.seasons.toTypedArray())
                clickEvent.trigger(schedule)
            }.loading {
                loading.set(true)
            }.onError {
                loading.set(false)
            }
        }
    }

    override fun onCleared() {
        scheduleUseCase.dispose()
        showSeasonsUseCase.dispose()
        super.onCleared()
    }
}

interface ScheduleListeners {
    fun onClick(schedule: ShowScheduleResponse)
}
