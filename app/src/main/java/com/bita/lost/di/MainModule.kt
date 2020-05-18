package com.bita.lost.di

import com.and.base.net.createNetService
import com.bita.lost.ui.main.MainDataSource
import com.bita.lost.ui.main.MainRepository
import com.bita.lost.ui.main.MainRepositoryImpl
import com.bita.lost.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule
    get() = module {
        single { createNetService<MainDataSource>(get()) }
        single<MainRepository> { MainRepositoryImpl(get()) }
        viewModel { MainViewModel(get()) }
    }