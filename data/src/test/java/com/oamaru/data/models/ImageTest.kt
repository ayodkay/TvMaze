package com.oamaru.data.models

import org.junit.Assert.assertEquals
import org.junit.Test

class ImageTest {
    @Test
    fun testImage() {
        val image = Image("https://example.com/image.jpg", "https://example.com/image-full.jpg")
        assertEquals("https://example.com/image.jpg", image.medium)
        assertEquals("https://example.com/image-full.jpg", image.original)
    }
}
