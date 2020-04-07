package com.hoony.kotlinsample.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.io.InputStreamReader
import java.net.URL

class WeatherData {
    companion object {
        private val xmlPullParser by lazy { XmlPullParserFactory.newInstance() }

        suspend fun getCurrentWeather(latitude: Double, longitude: Double): String {
            return GlobalScope.async(Dispatchers.IO) {
                val requestUrl = "https://api.openweathermap.org/data/2.5/weather" +
                        "?lat=${latitude}&lon=${longitude}&mode=xml&unit=metric&" +
                        "&appid=f3e3a32bb46853c4232fe74eb8d31d8a"
                var currentWeather = ""

                try {
                    val url = URL(requestUrl)
                    val stream = url.openStream()

                    val parser = xmlPullParser.newPullParser()

                    parser.setInput(InputStreamReader(stream, "UTF-8"))

                    var eventType = parser.eventType
                    var currentWeatherCode = 0

                    while (eventType != XmlPullParser.END_DOCUMENT) {
                        if (eventType == XmlPullParser.START_TAG && parser.name == "weather") {
                            currentWeatherCode = parser.getAttributeValue(null, "number").toInt()
                            break;
                        }
                        eventType = parser.next()
                    }

                    when (currentWeatherCode) {
                        in 200..299 -> currentWeather = "뇌우"
                        in 300..399 -> currentWeather = "이슬비"
                        in 500..599 -> currentWeather = "비"
                        in 600..699 -> currentWeather = "눈"
                        in 700..761 -> currentWeather = "안개"
                        771 -> currentWeather = "돌풍"
                        781 -> currentWeather = "토네이도"
                        800 -> currentWeather = "맑음"
                        in 801..802 -> currentWeather = "구름 조금"
                        in 803..804 -> currentWeather = "구름 많음"
                        else -> currentWeather = ""
                    }
                } catch (e: Exception) {
                    println(e)
                }

                currentWeather
            }.await()
        }
    }
}