package com.hoony.kotlinsample.animation

import com.hoony.kotlinsample.animation.interpolator.InterpolatorActivity
import com.hoony.kotlinsample.data.TargetData
import com.hoony.kotlinsample.util.abstract_class.list_activity.AbsListActivity

class AnimationListActivity : AbsListActivity() {
    override fun getTargetDataList(): List<TargetData> = listOf(
        TargetData(
            InterpolatorActivity::class.java,
            "Interpolator"
        )
    )
}