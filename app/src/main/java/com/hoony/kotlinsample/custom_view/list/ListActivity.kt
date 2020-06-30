package com.hoony.kotlinsample.custom_view.list

import com.hoony.kotlinsample.custom_view.smile_emoticon_example.CustomViewActivity
import com.hoony.kotlinsample.data.TargetData
import com.hoony.kotlinsample.util.abstract_class.list_activity.AbsListActivity

class ListActivity : AbsListActivity() {
    override fun getTargetDataList(): List<TargetData> {
        return listOf(
            TargetData(
                CustomViewActivity::class.java,
                "Smile emoticon example"
            )
        )
    }
}