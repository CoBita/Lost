package com.bita.lost.di

import com.and.base.net.Net
import com.bita.lost.net.NetConst
import com.bita.lost.Const
import okhttp3.Interceptor
import org.koin.dsl.module

val netModule = module {

    val baseUrl = NetConst.BASE_URl
    val serviceKey = Const.SERVICE_KEY
    val type = "json"
    single { arrayOf<Interceptor>() }
    single { Net(baseUrl, interceptors = get()) }

}