package com.oamaru.data.models

import org.junit.Assert.assertEquals
import org.junit.Test

class ExternalsTest {
    @Test
    fun testExternals() {
        val externals = Externals("tt1234567", 1234, 5678)
        assertEquals("tt1234567", externals.imdb)
        assertEquals(1234, externals.thetvdb)
        assertEquals(5678, externals.tvrage)
    }
}
