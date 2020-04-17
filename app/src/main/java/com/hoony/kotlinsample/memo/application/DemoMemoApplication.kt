package com.hoony.kotlinsample.memo.application

import android.app.Application
import com.naver.maps.map.NaverMapSdk
import io.realm.Realm
import io.realm.RealmConfiguration

class DemoMemoApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        val config = RealmConfiguration.Builder()
            .schemaVersion(2)
            .migration { realm, oldVersion, _ ->
                val schema = realm.schema

                if (oldVersion <= 1L) {
                    val personSchema = schema.get("MemoData")
                    personSchema?.addField("longitude", Double::class.java, null)
                    personSchema?.removeField("longtude")
                }
            }
            .build()
        Realm.setDefaultConfiguration(config)
        NaverMapSdk.getInstance(this).client = NaverMapSdk.NaverCloudPlatformClient("21y2oms6ve")
    }
}