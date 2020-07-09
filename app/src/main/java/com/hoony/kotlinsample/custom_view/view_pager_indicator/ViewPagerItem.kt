package com.hoony.kotlinsample.custom_view.view_pager_indicator

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.view.View
import androidx.core.graphics.ColorUtils
import androidx.recyclerview.widget.RecyclerView
import com.hoony.kotlinsample.common.ToastPrinter
import kotlinx.android.synthetic.main.item_single_text_full_size.view.*
import kotlinx.android.synthetic.main.item_single_text_full_size.view.clContainer
import kotlinx.android.synthetic.main.item_single_text_full_size.view.tvTitle
import kotlinx.android.synthetic.main.item_single_text_gradient.view.*
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

    fun setBottomGradient() {
//        // 0~255 사이, 0, 25, 50, 75, 100%
//        val colors = intArrayOf(
//            ColorUtils.setAlphaComponent(Color.parseColor("#200802"), 255),
//            ColorUtils.setAlphaComponent(Color.parseColor("#200802"), 199),
//            ColorUtils.setAlphaComponent(Color.parseColor("#200802"), 143),
//            ColorUtils.setAlphaComponent(Color.parseColor("#200802"), 77),
//            ColorUtils.setAlphaComponent(Color.parseColor("#141517"), 0)
//        )
        // 0~255 사이, 0, 25, 50, 75, 100%
        val colors = intArrayOf(
            ColorUtils.setAlphaComponent(Color.parseColor("#ffffff"), 255),
            ColorUtils.setAlphaComponent(Color.parseColor("#200802"), 199),
            ColorUtils.setAlphaComponent(Color.parseColor("#200802"), 143),
            ColorUtils.setAlphaComponent(Color.parseColor("#200802"), 77),
            ColorUtils.setAlphaComponent(Color.parseColor("#200802"), 0)
        )
        val offsetArray: FloatArray = floatArrayOf(
            0f,
            0.26f,
            0.55f,
            0.81f,
            1f
        )
        val gradientDrawable = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            GradientDrawable().apply {
                orientation = GradientDrawable.Orientation.BOTTOM_TOP
                setColors(colors, offsetArray)
            }
        } else {
            GradientDrawable(GradientDrawable.Orientation.BOTTOM_TOP, colors)
        }

        view.viewBottomGradient.background = gradientDrawable
    }
}