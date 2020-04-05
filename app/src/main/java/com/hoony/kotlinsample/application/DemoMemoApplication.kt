package com.hoony.kotlinsample.application

import android.app.Application
import com.naver.maps.map.NaverMapSdk
import io.realm.Realm

class DemoMemoApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        NaverMapSdk.getInstance(this).client = NaverMapSdk.NaverCloudPlatformClient("21y2oms6ve")
    }
}