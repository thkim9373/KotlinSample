package com.hoony.kotlinsample.data

import java.util.*

class MemoData {
    var id: String = UUID.randomUUID().toString()
    var createAt: Date = Date()
    var title: String = ""
    var content: String = ""
    var summary: String = ""
    var imageFile: String = ""
    var latitude: Double = 0.0
    var longtude: Double = 0.0
    var alarmTime: Date = Date()
    var weather: String = ""
}