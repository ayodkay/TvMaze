package com.oamaru.domain.room

import com.oamaru.data.room.Favorite

interface RemoveFavoriteUseCase {
    fun execute(favorite: Favorite)
}
