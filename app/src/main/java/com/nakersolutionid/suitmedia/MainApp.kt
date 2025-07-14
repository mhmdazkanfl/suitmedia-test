package com.nakersolutionid.suitmedia

import android.app.Application
import com.nakersolutionid.suitmedia.di.networkModule
import com.nakersolutionid.suitmedia.di.repositoryModule
import com.nakersolutionid.suitmedia.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MainApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MainApp)
            modules(
                listOf(
                    networkModule,
                    repositoryModule,
                    viewModelModule
                )
            )
        }
    }
}