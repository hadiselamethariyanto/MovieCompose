package banyuwangi.digital.moviecompose.di

import banyuwangi.digital.core.domain.usecase.collections.CollectionInteractor
import banyuwangi.digital.core.domain.usecase.collections.CollectionUseCase
import banyuwangi.digital.core.domain.usecase.movie.MoviesInteractor
import banyuwangi.digital.core.domain.usecase.movie.MoviesUseCase
import banyuwangi.digital.core.domain.usecase.people.PeopleInteractor
import banyuwangi.digital.core.domain.usecase.people.PeopleUseCase
import banyuwangi.digital.core.domain.usecase.tv.TvInteractor
import banyuwangi.digital.core.domain.usecase.tv.TvUseCase
import banyuwangi.digital.core.domain.usecase.watchlist.WatchlistInteractor
import banyuwangi.digital.core.domain.usecase.watchlist.WatchlistUseCase
import banyuwangi.digital.moviecompose.ui.screen.detail_movie.DetailMovieViewModel
import banyuwangi.digital.moviecompose.ui.screen.detail_people.DetailPeopleViewModel
import banyuwangi.digital.moviecompose.ui.screen.detail_tv.DetailTvViewModel
import banyuwangi.digital.moviecompose.ui.screen.home.HomeViewModel
import banyuwangi.digital.moviecompose.ui.screen.watchlist.WatchlistViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val useCaseModule = module {
    factory<MoviesUseCase> { MoviesInteractor(get()) }
    factory<TvUseCase> { TvInteractor(get()) }
    factory<CollectionUseCase> { CollectionInteractor(get()) }
    factory<PeopleUseCase> { PeopleInteractor(get()) }
    factory<WatchlistUseCase> { WatchlistInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get(), get(), get(), get()) }
    viewModel { DetailMovieViewModel(get(), get(), get()) }
    viewModel { DetailTvViewModel(get(), get()) }
    viewModel { DetailPeopleViewModel(get(), get()) }
    viewModel { WatchlistViewModel(get())}
}