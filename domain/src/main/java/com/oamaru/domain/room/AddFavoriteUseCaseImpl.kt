package com.oamaru.domain.room

import com.oamaru.data.room.Favorite
import com.oamaru.data.room.FavoriteRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class AddFavoriteUseCaseImpl : AddFavoriteUseCase, KoinComponent {

    private val favoriteRepository: FavoriteRepository by inject()

    override fun execute(favorite: Favorite) {
        favoriteRepository.addFavorite(favorite)
    }
}
