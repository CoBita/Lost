package com.bita.lost.di

import com.and.base.net.Net
import com.bita.lost.net.CoronaRequestInterceptor
import com.bita.lost.net.CoronaResponseInterceptor
import com.bita.lost.net.HeaderInterceptor
import com.bita.lost.net.NetConst
import com.bita.lost.Const
import org.koin.dsl.module

val netModule = module {

    val baseUrl = NetConst.BASE_URl
    val serviceKey = Const.SERVICE_KEY
    val type = "json"
    single { arrayOf(CoronaResponseInterceptor(), HeaderInterceptor(), CoronaRequestInterceptor(serviceKey, type)) }
    single { Net(baseUrl, interceptors = get()) }

}