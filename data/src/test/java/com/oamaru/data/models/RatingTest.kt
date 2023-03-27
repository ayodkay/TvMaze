package com.oamaru.data.models

import org.junit.Assert.assertEquals
import org.junit.Test

class RatingTest {
    @Test
    fun testRating() {
        val rating = Rating(8.5)
        assertEquals(8.5, rating.average!!, 0.0)
    }
}
