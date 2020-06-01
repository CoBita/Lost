package com.bita.lost.di

import com.bita.lost.repo.AcquireRepository
import com.bita.lost.repo.AcquireRepositoryImpl
import com.bita.lost.repo.ColorRepository
import com.bita.lost.repo.ColorRepositoryImpl
import com.bita.lost.ui.main.AcquirePlaceViewModel
import com.bita.lost.ui.main.AcquisitionViewModel
import com.bita.lost.ui.main.ColorPickViewModel
import com.bita.lost.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule
    get() = module {

        single<AcquireRepository> { AcquireRepositoryImpl() }
        single<ColorRepository> { ColorRepositoryImpl() }

        viewModel { MainViewModel() }
        viewModel { AcquirePlaceViewModel(get()) }
        viewModel { AcquisitionViewModel(get()) }
        viewModel { ColorPickViewModel(get()) }

    }