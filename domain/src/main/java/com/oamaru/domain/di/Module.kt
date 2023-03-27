package com.oamaru.domain.di

import com.oamaru.domain.episode.EpisodeUseCase
import com.oamaru.domain.episode.EpisodeUseCaseImpl
import com.oamaru.domain.room.AddFavoriteUseCase
import com.oamaru.domain.room.AddFavoriteUseCaseImpl
import com.oamaru.domain.room.GetFavoritesUseCase
import com.oamaru.domain.room.GetFavoritesUseCaseImpl
import com.oamaru.domain.room.RemoveFavoriteUseCase
import com.oamaru.domain.room.RemoveFavoriteUseCaseImpl
import com.oamaru.domain.schedule.ScheduleUseCase
import com.oamaru.domain.schedule.ScheduleUseCaseImpl
import com.oamaru.domain.search.SearchUseCase
import com.oamaru.domain.search.SearchUseCaseImpl
import com.oamaru.domain.seasons.ShowSeasonsUseCase
import com.oamaru.domain.seasons.ShowSeasonsUseCaseImpl
import org.koin.dsl.module

val domainModule = module {
    single<ScheduleUseCase> { ScheduleUseCaseImpl() }
    single<ShowSeasonsUseCase> { ShowSeasonsUseCaseImpl() }
    single<EpisodeUseCase> { EpisodeUseCaseImpl() }
    single<SearchUseCase> { SearchUseCaseImpl() }

    single<AddFavoriteUseCase> { AddFavoriteUseCaseImpl() }
    single<RemoveFavoriteUseCase> { RemoveFavoriteUseCaseImpl() }
    single<GetFavoritesUseCase> { GetFavoritesUseCaseImpl() }
}
