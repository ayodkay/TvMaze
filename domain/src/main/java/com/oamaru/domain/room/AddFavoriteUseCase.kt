package com.oamaru.domain.room

import com.oamaru.data.room.Favorite

interface AddFavoriteUseCase {
    fun execute(favorite: Favorite)
}
