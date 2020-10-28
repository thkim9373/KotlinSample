package com.hoony.kotlinsample.kakao.message

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.hoony.kotlinsample.kakao.message.fragments.*

class MessageViewModel : ViewModel() {

    enum class MessageType(val fragment: Class<out Fragment>) {
        FEED(FeedFragment::class.java),
        List(ListFragment::class.java),
        LOCATION(LocationFragment::class.java),
        COMMERCE(CommerceFragment::class.java),
        TEXT(TextFragment::class.java),
    }


}