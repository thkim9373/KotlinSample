package com.hoony.kotlinsample.custom_view.layout_contain_view_pager_example

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewpager2.widget.ViewPager2
import com.hoony.kotlinsample.R
import kotlinx.android.synthetic.main.view_custom_view_pager.view.*

class CustomViewPagerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleInt: Int = 0
) : ConstraintLayout(context, attrs, defStyleInt) {

    init {
        LayoutInflater.from(context).inflate(R.layout.view_custom_view_pager, this, true)

        viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
    }

    fun setAdapter(adapter: ViewPagerAdapter) {
        viewPager.adapter = adapter
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        viewPager.adapter = null
    }
}