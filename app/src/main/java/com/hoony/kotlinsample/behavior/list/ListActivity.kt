package com.hoony.kotlinsample.behavior.list

import com.hoony.kotlinsample.behavior.scroll_example_1.BehaviorExampleActivity3
import com.hoony.kotlinsample.behavior.scroll_example_2.BehaviorExampleActivity4
import com.hoony.kotlinsample.behavior.scroll_example_3.BehaviorExampleActivity5
import com.hoony.kotlinsample.data.TargetData
import com.hoony.kotlinsample.util.abstract_class.list.AbsListActivity

class ListActivity : AbsListActivity() {

    private val targetDataList: List<TargetData> = listOf(
        TargetData(
            BehaviorExampleActivity3::class.java,
            "Scroll example 1"
        ),
        TargetData(
            BehaviorExampleActivity4::class.java,
            "Scroll example 2"
        ),
        TargetData(
            BehaviorExampleActivity5::class.java,
            "Scroll example 3"
        )
    )

    override fun getTargetDataList(): List<TargetData> {
        return targetDataList
    }
}