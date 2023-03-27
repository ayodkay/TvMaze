package com.oamaru.data.repo.schedule

import com.jakewharton.rxrelay2.BehaviorRelay
import com.oamaru.data.models.response.ShowScheduleResponse
import com.oamaru.data.status.Status

interface ScheduleRepository {
    fun getTodaySchedule(date: String): BehaviorRelay<Status<List<ShowScheduleResponse>>>
}
