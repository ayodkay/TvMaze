package com.oamaru.data.di

import com.oamaru.data.repo.episode.ShowEpisodeRepository
import com.oamaru.data.repo.episode.ShowEpisodeRepositoryImpl
import com.oamaru.data.repo.schedule.ScheduleRepository
import com.oamaru.data.repo.schedule.ScheduleRepositoryImpl
import com.oamaru.data.repo.search.SearchRepository
import com.oamaru.data.repo.search.SearchRepositoryImpl
import com.oamaru.data.repo.season.ShowSeasonRepository
import com.oamaru.data.repo.season.ShowSeasonRepositoryImpl
import org.koin.dsl.module

val dataModule = module {
    single<ScheduleRepository> { ScheduleRepositoryImpl() }
    single<ShowSeasonRepository> { ShowSeasonRepositoryImpl() }
    single<ShowEpisodeRepository> { ShowEpisodeRepositoryImpl() }
    single<SearchRepository> { SearchRepositoryImpl() }
}
