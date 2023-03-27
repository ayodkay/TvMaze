package com.oamaru.data.models.response

import com.oamaru.data.models.Rating
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertNull
import org.junit.Test

class ShowEpisodeResponseTest {

    @Test
    fun testShowEpisodeResponse() {
        val response = ShowEpisodeResponse(
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

        // Verify the values of the object
        assertEquals("2023-03-28", response.airdate)
        assertEquals("2023-03-28T21:00:00+00:00", response.airstamp)
        assertEquals("21:00", response.airtime)
        assertEquals(12345, response.id)
        assertNull(response.image)
        assertNull(response.links)
        assertEquals("Episode Name", response.name)
        assertEquals(1, response.number)
        assertNotNull(response.rating)
        assertEquals(7.5, response.rating?.average ?: 0.0, 0.0)
        assertEquals(30, response.runtime)
        assertEquals(1, response.season)
        assertEquals("Episode summary", response.summary)
        assertEquals("regular", response.type)
        assertEquals("https://www.example.com/episode/12345", response.url)
    }
}
