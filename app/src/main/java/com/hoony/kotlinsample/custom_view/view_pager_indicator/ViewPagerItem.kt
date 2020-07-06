package com.hoony.kotlinsample.custom_view.view_pager_indicator

import android.graphics.Color
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.hoony.kotlinsample.common.ToastPrinter
import kotlinx.android.synthetic.main.item_single_text_full_size.view.*
import kotlin.random.Random

class ViewPagerItem(private val view: View) :
    RecyclerView.ViewHolder(view) {

    fun setText(title: String) {
        view.tvTitle.text = title
    }

    fun changeBackgroundColor() {
        val random = Random
        val color: Int =
            Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256))
        view.clContainer.setBackgroundColor(color)
    }

    fun setToastPrinter() {
        view.clContainer.setOnClickListener {
            ToastPrinter.showToast(
                view.context,
                "View pager item clicked. Position : ${adapterPosition + 1}"
            )
        }
    }
}