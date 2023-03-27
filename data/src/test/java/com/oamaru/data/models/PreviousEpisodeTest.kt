package com.oamaru.data.models

import org.junit.Assert.assertEquals
import org.junit.Test

class PreviousEpisodeTest {
    @Test
    fun testPreviousEpisode() {
        val previousEpisode = PreviousEpisode("https://example.com/episode")
        assertEquals("https://example.com/episode", previousEpisode.href)
    }
}
