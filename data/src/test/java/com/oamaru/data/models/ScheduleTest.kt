package com.oamaru.data.models

import org.junit.Assert.assertEquals
import org.junit.Test

class ScheduleTest {
    @Test
    fun testSchedule() {
        val days = listOf("Monday", "Tuesday", "Wednesday")
        val time = "8:00pm"
        val schedule = Schedule(days, time)

        assertEquals(days, schedule.days)
        assertEquals(time, schedule.time)
    }
}
