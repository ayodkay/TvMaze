package com.oamaru.data.repo

import com.oamaru.data.models.Country
import com.oamaru.data.models.Externals
import com.oamaru.data.models.Image
import com.oamaru.data.models.Network
import com.oamaru.data.models.NextEpisode
import com.oamaru.data.models.PreviousEpisode
import com.oamaru.data.models.Rating
import com.oamaru.data.models.Schedule
import com.oamaru.data.models.ScheduleShowLink
import com.oamaru.data.models.Self
import com.oamaru.data.models.Show
import com.oamaru.data.models.response.SearchResponse
import com.oamaru.data.repo.search.SearchRepositoryImpl
import com.oamaru.data.service.TvMazeService
import com.oamaru.data.status.Error
import com.oamaru.data.status.Status
import com.oamaru.data.status.toThrowable
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.rxjava3.core.Flowable
import junit.framework.TestCase
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import retrofit2.Response

class SearchRepositoryImplTest : KoinTest {
    private lateinit var searchRepositoryImpl: SearchRepositoryImpl
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
        searchRepositoryImpl = SearchRepositoryImpl()
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `search returns BehaviorRelay with Loading status and then Success status`() {
        // Mock a successful response from the tvMazeService

        val previousEpisode = PreviousEpisode("https://example.com/episode")
        val self = Self("https://example.com/show")
        val nextEpisode = NextEpisode("https://example.com/episode")
        val scheduleShowLink = ScheduleShowLink(nextEpisode, previousEpisode, self)

        val country = Country("US", "United States", "GMT-8")
        val network = Network(country, 1234, "ABC", "https://example.com")

        val days = listOf("Monday", "Tuesday", "Wednesday")
        val time = "8:00pm"
        val schedule = Schedule(days, time)

        val show = Show(
            30,
            null,
            "ended",
            Externals("tt1234567", 1234, 5678),
            listOf("Drama"),
            1,
            Image("https://example.com/image.jpg", "https://example.com/image-full.jpg"),
            "en",
            scheduleShowLink,
            "Under the Dome",
            network,
            "http://www.cbs.com/shows/under-the-dome/",
            "2013-06-24",
            Rating(8.5),
            60,
            schedule,
            "Ended",
            "An invisible and mysterious force field descends upon a small fictional town in the United States, trapping residents inside, cut off from the rest of civilization. Panic ensues as resources are quickly used up, and the town tries to understand the truth behind the mysterious barrier and find a way out.",
            "Scripted",
            1573667716,
            "http://www.tvmaze.com/shows/1/under-the-dome",
            null,
            null
        )
        val searchResponse = listOf(
            SearchResponse(0.6, show)
        )

        every { tvMazeService.search("modern") } returns Flowable.just(
            Response.success(
                searchResponse
            )
        )

        // Call the getMatches function and capture the emitted Status objects
        val statuses = mutableListOf<Status<List<SearchResponse>>>()
        val loading = Status.Loading<List<SearchResponse>>()
        statuses.add(loading)
        val dispose = searchRepositoryImpl.search("modern").subscribe { status ->
            statuses.add(status)
        }

        // Verify that the BehaviorRelay emits the expected Status objects
        Assert.assertEquals(2, statuses.size)
        TestCase.assertEquals(loading, statuses[0])
        TestCase.assertEquals(Status.OnSuccess(searchResponse), statuses[1])

        // Verify that the TvMazeService was called with the correct arguments
        verify { tvMazeService.search("modern") }
        dispose.dispose()
    }

    @Test
    fun `search returns BehaviorRelay with Loading status and then Error status`() {
        // Mock an error response from the tvMazeService
        val error = Error(code = 0, message = "Error message", cause = null).toThrowable()
        every { tvMazeService.search("modern") } returns Flowable.error(error)

        // Call the getMatches function and capture the emitted Status objects
        val statuses = mutableListOf<Status<List<SearchResponse>>>()
        val loading = Status.Loading<List<SearchResponse>>()
        statuses.add(loading)
        val dispose = searchRepositoryImpl.search("modern").subscribe { status ->
            statuses.add(status)
        }

        // Verify that the BehaviorRelay emits the expected Status objects
        Assert.assertEquals(2, statuses.size)
        TestCase.assertEquals(loading, statuses[0])
        TestCase.assertEquals(
            Status.OnError<SearchResponse>(Error(error)).error.message,
            (statuses[1] as Status.OnError).error.message
        )

        // Verify that the tvMazeService was called with the correct arguments
        verify { tvMazeService.search("modern") }

        dispose.dispose()
    }
}
