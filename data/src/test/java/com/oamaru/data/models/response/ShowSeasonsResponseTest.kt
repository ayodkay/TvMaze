package com.oamaru.data.models.response

import com.oamaru.data.models.Country
import com.oamaru.data.models.Image
import com.oamaru.data.models.Network
import com.oamaru.data.models.ShowLinks
import com.oamaru.data.models.WebChannel
import junit.framework.TestCase.assertEquals
import org.junit.Test

class ShowSeasonsResponseTest {

    @Test
    fun testShowSeasonsResponse() {
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

        val season = ShowSeasonsResponse(
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

        assertEquals(id, season.id)
        assertEquals(number, season.number)
        assertEquals(name, season.name)
        assertEquals(network, season.network)
        assertEquals(image, season.image)
        assertEquals(premiereDate, season.premiereDate)
        assertEquals(endDate, season.endDate)
        assertEquals(episodeOrder, season.episodeOrder)
        assertEquals(summary, season.summary)
        assertEquals(url, season.url)
        assertEquals(webChannel, season.webChannel)
        assertEquals(showLinks, season.links)
    }
}
