package com.oamaru.data.room

import android.app.Application
import androidx.room.Room
import com.jakewharton.rxrelay2.BehaviorRelay
import io.reactivex.Observable

class FavoriteRepository(application: Application) {
    private val favoriteDao: FavoriteDao
    private val favoritesSubject = BehaviorRelay.create<List<Favorite>>()

    init {
        val db = Room.databaseBuilder(
            application,
            FavoriteDatabase::class.java,
            "favorites-database"
        ).build()
        favoriteDao = db.favoriteDao()

        favoritesSubject.accept(favoriteDao.getAllFavorites())
    }

    fun getFavorites(): Observable<List<Favorite>> {
        return favoritesSubject
    }

    fun addFavorite(favorite: Favorite) {
        favoriteDao.insert(favorite)
        val currentFavorites = favoritesSubject.value ?: emptyList()
        val newFavorites = currentFavorites + favorite
        favoritesSubject.accept(newFavorites)
    }

    fun removeFavorite(favorite: Favorite) {
        favoriteDao.delete(favorite)
        val currentFavorites = favoritesSubject.value ?: emptyList()
        val newFavorites = currentFavorites - favorite
        favoritesSubject.accept(newFavorites)
    }
}
