package com.oamaru.data.models

import org.junit.Assert.assertEquals
import org.junit.Test

class ShowLinksTest {
    @Test
    fun testShowLinks() {
        val href = "https://example.com/show"
        val showLinks = ShowLinks(href)
        assertEquals(href, showLinks.href)
    }
}
