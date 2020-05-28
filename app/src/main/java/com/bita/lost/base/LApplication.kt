package com.bita.lost.base

import com.and.base.ui.BaseApplication
import com.bita.lost.R
import com.bita.lost.di.appModules
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.RequestConfiguration
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class LApplication : BaseApplication() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
        initAds()
    }

    private fun initKoin() {
        startKoin {
            if (IS_DEBUG) androidLogger()

            // Android Context given
            androidContext(this@LApplication)
            modules(appModules)
        }
    }

    private fun initAds() {
        MobileAds.initialize(this)
        if (!AdRequest.Builder().build().isTestDevice(this)) {
            val testDeviceIds = listOf(resources.getString(R.string.test_device_id_1), resources.getString(R.string.test_device_id_2))
            val configuration =
                RequestConfiguration.Builder().setTestDeviceIds(testDeviceIds).build()
            MobileAds.setRequestConfiguration(configuration)
        }
    }
}