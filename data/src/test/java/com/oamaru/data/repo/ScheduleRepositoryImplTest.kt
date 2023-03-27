package com.oamaru.data.repo

import com.oamaru.data.models.Country
import com.oamaru.data.models.DvdCountry
import com.oamaru.data.models.Externals
import com.oamaru.data.models.Image
import com.oamaru.data.models.Network
import com.oamaru.data.models.NextEpisode
import com.oamaru.data.models.PreviousEpisode
import com.oamaru.data.models.Rating
import com.oamaru.data.models.Schedule
import com.oamaru.data.models.ScheduleLinks
import com.oamaru.data.models.ScheduleShowLink
import com.oamaru.data.models.Self
import com.oamaru.data.models.Show
import com.oamaru.data.models.response.ShowScheduleResponse
import com.oamaru.data.repo.schedule.ScheduleRepositoryImpl
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

class ScheduleRepositoryImplTest : KoinTest {
    private lateinit var scheduleRepositoryImpl: ScheduleRepositoryImpl
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
        scheduleRepositoryImpl = ScheduleRepositoryImpl()
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `getTodaySchedule returns BehaviorRelay with Loading status and then Success status`() {
        // Mock a successful response from the tvMazeService

        val rating = Rating(7.8)
        val country = Country("US", "United States", "America/New_York")
        val network = Network(country, 1, "ABC", "http://www.abc.com/")
        val externals = Externals("tt0944947", 121361, 5)
        val image = Image(
            "http://static.tvmaze.com/uploads/images/medium_portrait/1/2668.jpg",
            "http://static.tvmaze.com/uploads/images/original_untouched/1/2668.jpg"
        )
        val schedule = Schedule(listOf("Monday"), "21:00")
        val previousEpisode = PreviousEpisode("http://api.tvmaze.com/episodes/185054")
        val nextEpisode = NextEpisode("https://example.com/episode")
        val self = Self("http://api.tvmaze.com/episodes/185055")
        val scheduleShowLink = ScheduleShowLink(nextEpisode, previousEpisode, self)
        val dvdCountry = DvdCountry("US", "United States", "America/New_York")
        val show = Show(
            averageRuntime = 60,
            dvdCountry = dvdCountry,
            ended = "2009-03-13",
            externals = externals,
            genres = listOf("Drama", "Action", "Fantasy"),
            id = 1,
            image = image,
            language = "English",
            links = scheduleShowLink,
            name = "Game of Thrones",
            network = network,
            officialSite = "http://www.hbo.com/game-of-thrones",
            premiered = "2011-04-17",
            rating = rating,
            runtime = 60,
            schedule = schedule,
            status = "Ended",
            summary = "Seven noble families fight for control of the mythical land of Westeros.",
            type = "Scripted",
            updated = 1589985696,
            url = "http://www.tvmaze.com/shows/82/game-of-thrones",
            webChannel = null,
            weight = 100
        )

        val scheduleLinks = ScheduleLinks(self, show)

        val showScheduleResponse = listOf(
            ShowScheduleResponse(
                airDate = "2011-04-18",
                airStamp = "2011-04-18T01:00:00-04:00",
                airtime = "21:00",
                id = 185055,
                image = null,
                links = scheduleLinks,
                name = "Winter Is Coming",
                number = 1,
                rating = rating,
                runtime = 60,
                season = 1,
                show = show,
                summary = "Series Premiere",
                type = "regular",
                url = "http://www.tvmaze.com/episodes/185055/game-of-thrones-1x01-winter-is-coming"
            )
        )

        every { tvMazeService.getTodaySchedule("2023-27-03") } returns Flowable.just(
            Response.success(
                showScheduleResponse
            )
        )

        // Call the getMatches function and capture the emitted Status objects
        val statuses = mutableListOf<Status<List<ShowScheduleResponse>>>()
        val loading = Status.Loading<List<ShowScheduleResponse>>()
        statuses.add(loading)
        val dispose = scheduleRepositoryImpl.getTodaySchedule("2023-27-03").subscribe { status ->
            statuses.add(status)
        }

        // Verify that the BehaviorRelay emits the expected Status objects
        Assert.assertEquals(2, statuses.size)
        TestCase.assertEquals(loading, statuses[0])
        TestCase.assertEquals(Status.OnSuccess(showScheduleResponse), statuses[1])

        // Verify that the TvMazeService was called with the correct arguments
        verify { tvMazeService.getTodaySchedule("2023-27-03") }
        dispose.dispose()
    }

    @Test
    fun `getTodaySchedule returns BehaviorRelay with Loading status and then Error status`() {
        // Mock an error response from the tvMazeService
        val error = Error(code = 0, message = "Error message", cause = null).toThrowable()
        every { tvMazeService.getTodaySchedule("2023-27-03") } returns Flowable.error(error)

        // Call the getMatches function and capture the emitted Status objects
        val statuses = mutableListOf<Status<List<ShowScheduleResponse>>>()
        val loading = Status.Loading<List<ShowScheduleResponse>>()
        statuses.add(loading)
        val dispose = scheduleRepositoryImpl.getTodaySchedule("2023-27-03").subscribe { status ->
            statuses.add(status)
        }

        // Verify that the BehaviorRelay emits the expected Status objects
        Assert.assertEquals(2, statuses.size)
        TestCase.assertEquals(loading, statuses[0])
        TestCase.assertEquals(
            Status.OnError<ShowScheduleResponse>(Error(error)).error.message,
            (statuses[1] as Status.OnError).error.message
        )

        // Verify that the tvMazeService was called with the correct arguments
        verify { tvMazeService.getTodaySchedule("2023-27-03") }

        dispose.dispose()
    }
}
