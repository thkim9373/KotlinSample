package com.hoony.kotlinsample.custom_view.layout_contain_view_pager_example

import android.content.Context
import android.util.AttributeSet
import com.hoony.kotlinsample.R

class ViewPagerItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : AbsItemView(context, attrs) {

    override fun init() {
        super.init()
    }

    override fun getLayoutId(): Int {
        return R.layout.item_single_text_full_size
    }
}