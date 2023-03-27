package com.oamaru.data.models

import org.junit.Assert.assertEquals
import org.junit.Test

class NetworkTest {
    @Test
    fun testNetwork() {
        val country = Country("US", "United States", "GMT-8")
        val network = Network(country, 1234, "ABC", "https://example.com")
        assertEquals(country, network.country)
        assertEquals(1234, network.id)
        assertEquals("ABC", network.name)
        assertEquals("https://example.com", network.officialSite)
    }
}
