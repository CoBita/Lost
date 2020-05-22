package com.bita.lost.di

import com.and.base.net.createNetService
import com.bita.lost.repo.DetailDataSource
import com.bita.lost.repo.DetailRepository
import com.bita.lost.repo.DetailRepositoryImpl
import com.bita.lost.ui.detail.DetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val detailModule = module {
    single<DetailDataSource> { createNetService(get()) }

    single<DetailRepository> { DetailRepositoryImpl(get()) }

    viewModel { DetailViewModel(get()) }
}