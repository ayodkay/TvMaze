package com.oamaru.data.models

import org.junit.Assert.assertEquals
import org.junit.Test

class ShowTest {
    @Test
    fun testShow() {
        val self = Self("https://example.com/show")
        val dvdCountry = DvdCountry("US", "United States", "America/New_York")

        val previousEpisode = PreviousEpisode("https://example.com/episode")
        val nextEpisode = NextEpisode("https://example.com/episode")
        val scheduleShowLink = ScheduleShowLink(nextEpisode, previousEpisode, self)
        val country = Country("US", "United States", "UTC-08:00")
        val network = Network(country, 1, "ABC", "https://abc.com")
        val rating = Rating(8.5)
        val schedule = Schedule(listOf("Monday", "Tuesday"), "8:00PM")
        val image = Image("https://example.com/image", "https://example.com/image-original")
        val externals = Externals("tt123456", 123456, 56789)
        val show = Show(
            averageRuntime = 60,
            dvdCountry = dvdCountry,
            ended = "2018",
            externals = externals,
            genres = listOf("Drama", "Thriller"),
            id = 1,
            image = image,
            language = "English",
            links = scheduleShowLink,
            name = "Show Name",
            network = network,
            officialSite = "https://example.com/official-site",
            premiered = "2018-01-01",
            rating = rating,
            runtime = 60,
            schedule = schedule,
            status = "Returning Series",
            summary = "A show summary.",
            type = "Scripted",
            updated = 123456,
            url = "https://example.com/show-url",
            webChannel = null,
            weight = 10
        )

        assertEquals(self, scheduleShowLink.self)
        assertEquals(60, show.averageRuntime)
        assertEquals(dvdCountry, show.dvdCountry)
        assertEquals("2018", show.ended)
        assertEquals(externals, show.externals)
        assertEquals(listOf("Drama", "Thriller"), show.genres)
        assertEquals(1, show.id)
        assertEquals(image, show.image)
        assertEquals("English", show.language)
        assertEquals("Show Name", show.name)
        assertEquals(network, show.network)
        assertEquals("https://example.com/official-site", show.officialSite)
        assertEquals("2018-01-01", show.premiered)
        assertEquals(rating, show.rating)
        assertEquals(60, show.runtime)
        assertEquals(schedule, show.schedule)
        assertEquals("Returning Series", show.status)
        assertEquals("A show summary.", show.summary)
        assertEquals("Scripted", show.type)
        assertEquals(123456, show.updated)
        assertEquals("https://example.com/show-url", show.url)
        assertEquals(null, show.webChannel)
        assertEquals(10, show.weight)
    }
}
