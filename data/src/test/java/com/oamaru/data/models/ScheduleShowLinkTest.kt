package com.oamaru.data.models

import org.junit.Assert.assertEquals
import org.junit.Test

class ScheduleShowLinkTest {
    @Test
    fun testScheduleShowLink() {
        val previousEpisode = PreviousEpisode("https://example.com/episode")
        val self = Self("https://example.com/show")
        val nextEpisode = NextEpisode("https://example.com/episode")
        val scheduleShowLink = ScheduleShowLink(nextEpisode, previousEpisode, self)

        assertEquals(previousEpisode, scheduleShowLink.previousEpisode)
        assertEquals(self, scheduleShowLink.self)
    }
}
