package com.oamaru.data.models

import org.junit.Assert.assertEquals
import org.junit.Test

class CountryTest {

    @Test
    fun testCountry() {
        val country = Country("US", "United States", "GMT-8")
        assertEquals("US", country.code)
        assertEquals("United States", country.name)
        assertEquals("GMT-8", country.timezone)
    }
}
