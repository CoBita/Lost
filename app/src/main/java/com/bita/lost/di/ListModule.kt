package com.bita.lost.di

import com.and.base.net.createNetService
import com.bita.lost.repo.ListDataSource
import com.bita.lost.repo.ListRepository
import com.bita.lost.repo.ListRepositoryImpl
import com.bita.lost.ui.list.ListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val listModule
    get() = module {
        single { createNetService<ListDataSource>(get()) }
        single<ListRepository> { ListRepositoryImpl(get()) }

        viewModel { ListViewModel(get()) }
    }