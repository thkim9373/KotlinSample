package com.hoony.kotlinsample.fragments

import com.hoony.kotlinsample.data.TargetData
import com.hoony.kotlinsample.util.abstract_class.list_activity.AbsListActivity

class ExampleListActivity : AbsListActivity() {
    override fun getTargetDataList(): List<TargetData> = listOf(
        TargetData(
            CustomDialogFragmentActivity::class.java,
            "Custom dialog fragment"
        ),
    )
}