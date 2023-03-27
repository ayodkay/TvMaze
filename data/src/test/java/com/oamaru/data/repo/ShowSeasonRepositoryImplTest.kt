package com.oamaru.data.repo

import com.oamaru.data.models.Country
import com.oamaru.data.models.Image
import com.oamaru.data.models.Network
import com.oamaru.data.models.ShowLinks
import com.oamaru.data.models.WebChannel
import com.oamaru.data.models.response.ShowSeasonsResponse
import com.oamaru.data.repo.season.ShowSeasonRepositoryImpl
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

class ShowSeasonRepositoryImplTest : KoinTest {
    private lateinit var showSeasonRepositoryImpl: ShowSeasonRepositoryImpl
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
        showSeasonRepositoryImpl = ShowSeasonRepositoryImpl()
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `getSeason returns BehaviorRelay with Loading status and then Success status`() {
        // Mock a successful response from the tvMazeService

        val id = 1
        val number = 2
        val name = "Season 2"

        val country = Country("US", "United States", "GMT-8")
        val network = Network(country, 1234, "ABC", "https://example.com")
        val image = Image("https://example.com/image.jpg", "https://example.com/image.jpg")
        val premiereDate = "2022-01-01"
        val endDate = "2022-05-01"
        val episodeOrder = 12
        val summary = "This is the summary of Season 2"
        val url = "https://example.com/seasons/2"
        val webChannel = WebChannel(country, 123, "Web Channel", "https://www.example.com")
        val href = "https://example.com/show"
        val showLinks = ShowLinks(href)

        val season = listOf(
            ShowSeasonsResponse(
                endDate = endDate,
                episodeOrder = episodeOrder,
                id = id,
                image = image,
                links = showLinks,
                name = name,
                network = network,
                number = number,
                premiereDate = premiereDate,
                summary = summary,
                url = url,
                webChannel = webChannel
            )
        )

        every { tvMazeService.getShowSeasons(1111) } returns Flowable.just(
            Response.success(
                season
            )
        )

        // Call the getMatches function and capture the emitted Status objects
        val statuses = mutableListOf<Status<List<ShowSeasonsResponse>>>()
        val loading = Status.Loading<List<ShowSeasonsResponse>>()
        statuses.add(loading)
        val dispose = showSeasonRepositoryImpl.getSeason(1111).subscribe { status ->
            statuses.add(status)
        }

        // Verify that the BehaviorRelay emits the expected Status objects
        Assert.assertEquals(2, statuses.size)
        TestCase.assertEquals(loading, statuses[0])
        TestCase.assertEquals(Status.OnSuccess(season), statuses[1])

        // Verify that the TvMazeService was called with the correct arguments
        verify { tvMazeService.getShowSeasons(1111) }
        dispose.dispose()
    }

    @Test
    fun `getSeason returns BehaviorRelay with Loading status and then Error status`() {
        // Mock an error response from the tvMazeService
        val error = Error(code = 0, message = "Error message", cause = null).toThrowable()
        every { tvMazeService.getShowSeasons(1111) } returns Flowable.error(error)

        // Call the getMatches function and capture the emitted Status objects
        val statuses = mutableListOf<Status<List<ShowSeasonsResponse>>>()
        val loading = Status.Loading<List<ShowSeasonsResponse>>()
        statuses.add(loading)
        val dispose = showSeasonRepositoryImpl.getSeason(1111).subscribe { status ->
            statuses.add(status)
        }

        // Verify that the BehaviorRelay emits the expected Status objects
        Assert.assertEquals(2, statuses.size)
        TestCase.assertEquals(loading, statuses[0])
        TestCase.assertEquals(
            Status.OnError<ShowSeasonsResponse>(Error(error)).error.message,
            (statuses[1] as Status.OnError).error.message
        )

        // Verify that the tvMazeService was called with the correct arguments
        verify { tvMazeService.getShowSeasons(1111) }

        dispose.dispose()
    }
}
