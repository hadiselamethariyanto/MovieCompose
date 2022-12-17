package banyuwangi.digital.moviecompose

import android.app.Application
import banyuwangi.digital.core.di.databaseModule
import banyuwangi.digital.core.di.networkModule
import banyuwangi.digital.core.di.repositoryModule
import banyuwangi.digital.moviecompose.di.useCaseModule
import banyuwangi.digital.moviecompose.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@App)
            modules(
                listOf(
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule,
                    databaseModule
                )
            )
        }
    }
}