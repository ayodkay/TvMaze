package com.oamaru.domain.room

import com.oamaru.data.room.Favorite
import io.reactivex.Observable

interface GetFavoritesUseCase {
    fun execute(): Observable<List<Favorite>>
}
