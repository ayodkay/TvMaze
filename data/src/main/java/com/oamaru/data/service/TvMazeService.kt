package com.oamaru.data.service

import com.oamaru.data.models.response.SearchResponse
import com.oamaru.data.models.response.ShowEpisodeResponse
import com.oamaru.data.models.response.ShowScheduleResponse
import com.oamaru.data.models.response.ShowSeasonsResponse
import io.reactivex.rxjava3.core.Flowable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TvMazeService {
    @GET("schedule")
    fun getTodaySchedule(@Query("date") date: String): Flowable<Response<List<ShowScheduleResponse>>>

    @GET("shows/{showId}/seasons")
    fun getShowSeasons(@Path("showId") showId: Int): Flowable<Response<List<ShowSeasonsResponse>>>

    @GET("seasons/{seasonId}/episodes")
    fun getShowEpisodes(@Path("seasonId") seasonId: Int): Flowable<Response<List<ShowEpisodeResponse>>>

    @GET("search/shows")
    fun search(@Query("q") query: String): Flowable<Response<List<SearchResponse>>>
}
