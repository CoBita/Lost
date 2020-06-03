package com.bita.lost.di

import com.bita.lost.repo.*
import com.bita.lost.ui.main.*
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule
    get() = module {

        single<ColorRepository> { ColorRepositoryImpl() }
        single<AreaRepository> { AreaRepositoryImpl() }
        single<ProductRepository> { ProductRepositoryImpl() }

        viewModel { ColorPickViewModel(get()) }
        viewModel { AreaViewModel(get()) }
        viewModel { ProductViewModel(get()) }

        viewModel { SearchViewModel() }


    }