package com.hoony.kotlinsample.main

import com.hoony.kotlinsample.content_provider.list.ListActivity
import com.hoony.kotlinsample.custom_view.CustomViewActivity
import com.hoony.kotlinsample.dagger.list.DaggerExampleListActivity
import com.hoony.kotlinsample.data.TargetData
import com.hoony.kotlinsample.memo.intro.IntroActivity
import com.hoony.kotlinsample.room.RoomActivity
import com.hoony.kotlinsample.rx_java.list.RxJavaExampleListActivity
import com.hoony.kotlinsample.saved_state_view_model.SavedStateViewModelActivity
import com.hoony.kotlinsample.sticky_item_decoration.StickyItemDecorationActivity
import com.hoony.kotlinsample.ui_example.list.UiListActivity
import com.hoony.kotlinsample.util.abstract_class.list_activity.AbsListActivity

class MainActivity : AbsListActivity() {

    private val targetList: List<TargetData> = arrayListOf(
        TargetData(IntroActivity::class.java, "Memo example"),
        TargetData(RoomActivity::class.java, "Room - Live Data example"),
        TargetData(ListActivity::class.java, "Content provider example"),
        TargetData(CustomViewActivity::class.java, "Custom view example"),
        TargetData(SavedStateViewModelActivity::class.java, "Saved state view model example"),
        TargetData(DaggerExampleListActivity::class.java, "Dagger examples"),
        TargetData(RxJavaExampleListActivity::class.java, "Rx java examples"),
        TargetData(UiListActivity::class.java, "UI examples"),
        TargetData(StickyItemDecorationActivity::class.java, "Sticky item decoration example"),
        TargetData(com.hoony.kotlinsample.behavior.list.ListActivity::class.java, "Behavior examples")
    )

    override fun getTargetDataList(): List<TargetData> {
        return targetList
    }
}