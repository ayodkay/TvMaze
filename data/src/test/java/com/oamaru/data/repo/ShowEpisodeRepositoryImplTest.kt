package com.oamaru.data.repo

import com.oamaru.data.models.Rating
import com.oamaru.data.models.response.ShowEpisodeResponse
import com.oamaru.data.repo.episode.ShowEpisodeRepositoryImpl
import com.oamaru.data.service.TvMazeService
import com.oamaru.data.status.Error
import com.oamaru.data.status.Status
import com.oamaru.data.status.toThrowable
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.rxjava3.core.Flowable
import junit.framework.TestCase.assertEquals
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import retrofit2.Response

class ShowEpisodeRepositoryImplTest : KoinTest {
    private lateinit var showEpisodeRepositoryImpl: ShowEpisodeRepositoryImpl
    private lateinit var tvMazeService: TvMazeService

    @Before
    fun setUp() {
        tvMazeService = mockk()
        startKoin {
            modules(
                module {
                    single { tvMazeService }
                }
            )
        }
        showEpisodeRepositoryImpl = ShowEpisodeRepositoryImpl()
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `getShowEpisodes returns BehaviorRelay with Loading status and then Success status`() {
        // Mock a successful response from the tvMazeService
        val episodes = listOf(
            ShowEpisodeResponse(
                airdate = "2023-03-28",
                airstamp = "2023-03-28T21:00:00+00:00",
                airtime = "21:00",
                id = 12345,
                image = null,
                links = null,
                name = "Episode Name",
                number = 1,
                rating = Rating(average = 7.5),
                runtime = 30,
                season = 1,
                summary = "Episode summary",
                type = "regular",
                url = "https://www.example.com/episode/12345"
            )
        )

        every { tvMazeService.getShowEpisodes(1111) } returns Flowable.just(
            Response.success(
                episodes
            )
        )

        // Call the getMatches function and capture the emitted Status objects
        val statuses = mutableListOf<Status<List<ShowEpisodeResponse>>>()
        val loading = Status.Loading<List<ShowEpisodeResponse>>()
        statuses.add(loading)
        val dispose = showEpisodeRepositoryImpl.getEpisodes(1111).subscribe { status ->
            statuses.add(status)
        }

        // Verify that the BehaviorRelay emits the expected Status objects
        Assert.assertEquals(2, statuses.size)
        assertEquals(loading, statuses[0])
        assertEquals(Status.OnSuccess(episodes), statuses[1])

        // Verify that the TvMazeService was called with the correct arguments
        verify { tvMazeService.getShowEpisodes(1111) }
        dispose.dispose()
    }

    @Test
    fun `getShowEpisodes returns BehaviorRelay with Loading status and then Error status`() {
        // Mock an error response from the tvMazeService
        val error = Error(code = 0, message = "Error message", cause = null).toThrowable()
        every { tvMazeService.getShowEpisodes(1111) } returns Flowable.error(error)

        // Call the getMatches function and capture the emitted Status objects
        val statuses = mutableListOf<Status<List<ShowEpisodeResponse>>>()
        val loading = Status.Loading<List<ShowEpisodeResponse>>()
        statuses.add(loading)
        val dispose = showEpisodeRepositoryImpl.getEpisodes(1111).subscribe { status ->
            statuses.add(status)
        }

        // Verify that the BehaviorRelay emits the expected Status objects
        Assert.assertEquals(2, statuses.size)
        assertEquals(loading, statuses[0])
        assertEquals(
            Status.OnError<ShowEpisodeResponse>(Error(error)).error.message,
            (statuses[1] as Status.OnError).error.message
        )

        // Verify that the tvMazeService was called with the correct arguments
        verify { tvMazeService.getShowEpisodes(1111) }

        dispose.dispose()
    }
}
