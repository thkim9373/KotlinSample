package com.hoony.kotlinsample.kakao

import com.hoony.kotlinsample.data.TargetData
import com.hoony.kotlinsample.kakao.message.MessageActivity
import com.hoony.kotlinsample.util.abstract_class.list_activity.AbsListActivity

class KakaoListActivity : AbsListActivity() {
    override fun getTargetDataList(): List<TargetData> = listOf(
        TargetData(
            MessageActivity::class.java,
            "Message"
        )
    )
}