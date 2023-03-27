package com.oamaru.domain.room

import com.oamaru.data.room.Favorite
import com.oamaru.data.room.FavoriteRepository
import io.reactivex.Observable
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetFavoritesUseCaseImpl() : GetFavoritesUseCase, KoinComponent {

    private val favoriteRepository: FavoriteRepository by inject()

    override fun execute(): Observable<List<Favorite>> {
        return favoriteRepository.getFavorites()
    }
}
