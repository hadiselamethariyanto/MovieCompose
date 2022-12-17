package banyuwangi.digital.core.di

import androidx.room.Room
import banyuwangi.digital.core.BuildConfig
import banyuwangi.digital.core.data.collection.repository.CollectionRepositoryImpl
import banyuwangi.digital.core.data.collection.repository.source.remote.CollectionRemoteDataSource
import banyuwangi.digital.core.data.collection.repository.source.remote.network.CollectionApiService
import banyuwangi.digital.core.data.movie.repository.MovieRepositoryImpl
import banyuwangi.digital.core.data.movie.repository.source.remote.MovieRemoteDataSource
import banyuwangi.digital.core.data.movie.repository.source.remote.network.MovieApiService
import banyuwangi.digital.core.data.people.repository.PeopleRepositoryImpl
import banyuwangi.digital.core.data.people.repository.source.remote.PeopleRemoteDataSource
import banyuwangi.digital.core.data.people.repository.source.remote.network.PeopleApiService
import banyuwangi.digital.core.data.tv.repository.TvRepositoryImpl
import banyuwangi.digital.core.data.tv.repository.source.remote.TvRemoteDataSource
import banyuwangi.digital.core.data.tv.repository.source.remote.network.TvApiService
import banyuwangi.digital.core.data.watchlist.repository.WatchlistRepositoryImpl
import banyuwangi.digital.core.data.watchlist.repository.source.local.MovieLocalDataSource
import banyuwangi.digital.core.data.watchlist.repository.source.local.room.MovieDatabase
import banyuwangi.digital.core.domain.repository.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<MovieDatabase>().movieDao() }

    single {
        Room.databaseBuilder(
            androidContext(),
            MovieDatabase::class.java, "movie.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(MovieApiService::class.java)
    }

    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(TvApiService::class.java)
    }

    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(CollectionApiService::class.java)
    }

    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(PeopleApiService::class.java)
    }
}

val repositoryModule = module {
    single { MovieRemoteDataSource(get()) }
    single { TvRemoteDataSource(get()) }
    single { CollectionRemoteDataSource(get()) }
    single { PeopleRemoteDataSource(get()) }
    single { MovieLocalDataSource(get()) }

    single<MoviesRepository> { MovieRepositoryImpl(get()) }
    single<TvRepository> { TvRepositoryImpl(get()) }
    single<CollectionRepository> { CollectionRepositoryImpl(get()) }
    single<PeopleRepository> { PeopleRepositoryImpl(get()) }
    single<WatchlistRepository> { WatchlistRepositoryImpl(get()) }
}