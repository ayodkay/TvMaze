package com.oamaru.data.models

import org.junit.Assert.assertEquals
import org.junit.Test

class ScheduleLinksTest {
    @Test
    fun testScheduleLinks() {
        val selfHref = Self("https://example.com/self")
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

        val scheduleLinks = ScheduleLinks(selfHref, show)

        assertEquals(selfHref, scheduleLinks.self)
        assertEquals(show, scheduleLinks.show)
    }
}
