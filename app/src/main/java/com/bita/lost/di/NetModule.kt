package com.bita.lost.di

import com.and.base.net.Net
import com.bita.lost.net.NetConst
import okhttp3.Interceptor
import org.koin.dsl.module

val netModule = module {
    val baseUrl = NetConst.BASE_URl
    single { Net(baseUrl) }

}