package com.hoony.kotlinsample.behavior.list

import com.hoony.kotlinsample.behavior.floating_button_example.BehaviorActivity1
import com.hoony.kotlinsample.behavior.nested_scroll_view_example.BehaviorActivity2
import com.hoony.kotlinsample.behavior.scroll_example_1.BehaviorExampleActivity3
import com.hoony.kotlinsample.behavior.scroll_example_2.BehaviorExampleActivity4
import com.hoony.kotlinsample.behavior.scroll_example_3.BehaviorExampleActivity5
import com.hoony.kotlinsample.data.TargetData
import com.hoony.kotlinsample.util.abstract_class.list_activity.AbsListActivity

class ListActivity : AbsListActivity() {

    private val targetDataList: List<TargetData> = listOf(
        TargetData(
            BehaviorActivity1::class.java,
            "Floating button example"
        ),
        TargetData(
            BehaviorActivity2::class.java,
            "Nested scroll view example"
        ),
        TargetData(
            BehaviorExampleActivity3::class.java,
            "Scroll example 1(with text view)"
        ),
        TargetData(
            BehaviorExampleActivity4::class.java,
            "Scroll example 2"
        ),        TargetData(
            BehaviorExampleActivity5::class.java,
            "Scroll example 3"
        )
    )

    override fun getTargetDataList(): List<TargetData> {
        return targetDataList
    }
}