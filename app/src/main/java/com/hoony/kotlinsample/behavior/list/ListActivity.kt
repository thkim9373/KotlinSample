package com.hoony.kotlinsample.behavior.list

import com.hoony.kotlinsample.behavior.scroll_example.BehaviorExampleActivity3
import com.hoony.kotlinsample.data.TargetData
import com.hoony.kotlinsample.util.abstract_class.list.AbsListActivity

class ListActivity : AbsListActivity() {

    private val targetDataList: List<TargetData> = listOf(
        TargetData(
            BehaviorExampleActivity3::class.java,
            "Scroll example"
        )
    )

    override fun getTargetDataList(): List<TargetData> {
        return targetDataList
    }
}