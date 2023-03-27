package com.oamaru.data.models

import org.junit.Assert.assertEquals
import org.junit.Test

class SelfTest {
    @Test
    fun testSelf() {
        val href = "https://example.com/self"
        val self = Self(href)

        assertEquals(href, self.href)
    }
}
