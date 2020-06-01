package com.bita.lost.di

import com.bita.lost.repo.*
import com.bita.lost.ui.main.*
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule
    get() = module {

        single<AcquireRepository> { AcquireRepositoryImpl() }
        single<ColorRepository> { ColorRepositoryImpl() }
        single<AreaRepository> { AreaRepositoryImpl() }

        viewModel { MainViewModel() }
        viewModel { AcquirePlaceViewModel(get()) }
        viewModel { AcquisitionViewModel(get()) }
        viewModel { ColorPickViewModel(get()) }
        viewModel { AreaViewModel(get()) }

        viewModel { SearchViewModel() }


    }