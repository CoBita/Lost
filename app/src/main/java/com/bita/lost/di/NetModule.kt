package com.bita.lost.di

import com.and.base.net.Net
import com.bita.lost.net.NetConst
import com.bita.lost.Const
import org.koin.dsl.module

val netModule = module {

    val baseUrl = NetConst.BASE_URl
    val serviceKey = Const.SERVICE_KEY
    single { Net(baseUrl, interceptors = get()) }

}