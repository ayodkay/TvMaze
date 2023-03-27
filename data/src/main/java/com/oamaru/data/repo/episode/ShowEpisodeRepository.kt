package com.oamaru.data.repo.episode

import com.jakewharton.rxrelay2.BehaviorRelay
import com.oamaru.data.models.response.ShowEpisodeResponse
import com.oamaru.data.status.Status

interface ShowEpisodeRepository {
    fun getEpisodes(seasonId: Int): BehaviorRelay<Status<List<ShowEpisodeResponse>>>
}
