package com.bita.lost.di

import com.bita.lost.repo.MainRepository
import com.bita.lost.repo.MainRepositoryImpl
import com.bita.lost.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule
    get() = module {
        single<MainRepository> { MainRepositoryImpl() }
        viewModel { MainViewModel(get()) }
    }