package com.oamaru.data.models

import junit.framework.TestCase.assertEquals
import org.junit.Test

class WebChannelTest {
    @Test
    fun testWebChannel() {
        val country = Country("US", "United States", "America/New_York")
        val webChannel = WebChannel(country, 123, "Web Channel", "https://www.example.com")
        assertEquals(country, webChannel.country)
        assertEquals(123, webChannel.id)
        assertEquals("Web Channel", webChannel.name)
        assertEquals("https://www.example.com", webChannel.officialSite)
    }
}
