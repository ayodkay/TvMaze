package com.oamaru.data.models.response

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
import org.junit.Assert
import org.junit.Test

class SearchResponseTest {

    @Test
    fun testSearchResponse() {
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
        val searchResponse = SearchResponse(0.6, show)

        // Test the properties of the SearchResponse object
        Assert.assertEquals(0.6, searchResponse.score, 0.001)
        Assert.assertEquals(show, searchResponse.show)
    }
}
