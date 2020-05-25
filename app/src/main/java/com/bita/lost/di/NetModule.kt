package com.bita.lost.di

import com.and.base.net.Net
import com.bita.lost.Const
import com.bita.lost.net.HeaderInterceptor
import com.bita.lost.net.LostRequestInterceptor
import com.bita.lost.net.LostResponseInterceptor
import com.bita.lost.net.NetConst
import okhttp3.Interceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module

val netModule = module {
    val baseUrl = NetConst.BASE_URl
    single { arrayOf(HeaderInterceptor(), LostRequestInterceptor(Const.SERVICE_KEY, "json"), LostResponseInterceptor()) }
    single(qualifier = named(listNet)) { Net(baseUrl, interceptors = get()) }
    single { Net(baseUrl) }

}