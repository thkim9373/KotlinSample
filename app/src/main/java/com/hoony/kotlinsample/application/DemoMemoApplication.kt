package com.hoony.kotlinsample.application

import android.app.Application
import io.realm.Realm

class DemoMemoApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
    }
}