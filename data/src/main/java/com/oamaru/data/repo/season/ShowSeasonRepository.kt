package com.oamaru.data.repo.season

import com.jakewharton.rxrelay2.BehaviorRelay
import com.oamaru.data.models.response.ShowSeasonsResponse
import com.oamaru.data.status.Status

interface ShowSeasonRepository {
    fun getSeason(showId: Int): BehaviorRelay<Status<List<ShowSeasonsResponse>>>
}
