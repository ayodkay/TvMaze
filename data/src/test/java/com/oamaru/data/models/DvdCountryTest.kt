package com.oamaru.data.models

import org.junit.Assert.assertEquals
import org.junit.Test

class DvdCountryTest {

    @Test
    fun testDvdCountry() {
        // Create a DvdCountry object with test data
        val dvdCountry = DvdCountry("US", "United States", "America/New_York")

        // Test the properties of the DvdCountry object
        assertEquals("US", dvdCountry.code)
        assertEquals("United States", dvdCountry.name)
        assertEquals("America/New_York", dvdCountry.timezone)
    }
}
