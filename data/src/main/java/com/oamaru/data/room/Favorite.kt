package com.oamaru.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class Favorite(
    @PrimaryKey val id: String,
    val name: String,
    val image: String,
    val description: String
)
