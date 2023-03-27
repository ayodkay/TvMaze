package com.oamaru.data.models.response

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
import org.junit.Assert.assertEquals
import org.junit.Test

class ShowScheduleResponseTest {
    @Test
    fun testShowScheduleResponse() {
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

        val showScheduleResponse = ShowScheduleResponse(
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

        assertEquals(scheduleLinks, showScheduleResponse.links)
        assertEquals("2011-04-18", showScheduleResponse.airDate)
        assertEquals("2011-04-18T01:00:00-04:00", showScheduleResponse.airStamp)
        assertEquals("21:00", showScheduleResponse.airtime)
        assertEquals(185055, showScheduleResponse.id)
        assertEquals(null, showScheduleResponse.image)
        assertEquals("Winter Is Coming", showScheduleResponse.name)
        assertEquals(1, showScheduleResponse.number)
        assertEquals(rating, showScheduleResponse.rating)
        assertEquals(60, showScheduleResponse.runtime)
        assertEquals(1, showScheduleResponse.season)
        assertEquals(show, showScheduleResponse.show)
        assertEquals("Series Premiere", showScheduleResponse.summary)
        assertEquals("regular", showScheduleResponse.type)
        assertEquals(
            "http://www.tvmaze.com/episodes/185055/game-of-thrones-1x01-winter-is-coming",
            showScheduleResponse.url
        )
    }
}
