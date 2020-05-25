package com.bita.lost.di

import com.and.base.net.createNetService
import com.bita.lost.repo.ListDataSource
import com.bita.lost.repo.ListRepository
import com.bita.lost.repo.ListRepositoryImpl
import com.bita.lost.ui.list.ListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val listNet = "listNet"

val listModule
    get() = module {
        single { createNetService<ListDataSource>(get(qualifier = named(listNet))) }
        single<ListRepository> { ListRepositoryImpl(get()) }

        viewModel { ListViewModel(get()) }
    }