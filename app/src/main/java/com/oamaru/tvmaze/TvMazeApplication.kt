package com.oamaru.tvmaze

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import com.oamaru.data.di.dataModule
import com.oamaru.data.service.TvMazeServiceModule
import com.oamaru.domain.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class TvMazeApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)

        startKoin {
            // Log Koin into Android logger
            androidLogger()
            // Reference Android context
            androidContext(this@TvMazeApplication)
            // Load modules
            modules(dataModule, domainModule, TvMazeServiceModule().tvMazeModule())
        }
    }
}
