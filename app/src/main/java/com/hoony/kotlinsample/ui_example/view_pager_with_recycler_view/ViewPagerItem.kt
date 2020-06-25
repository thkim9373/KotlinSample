package com.hoony.kotlinsample.ui_example.view_pager_with_recycler_view

import android.graphics.Color
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_single_text_full_size.view.*
import kotlin.random.Random

class ViewPagerItem(private val view: View) :
    RecyclerView.ViewHolder(view) {

    init {
        view.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {

                when(event?.action) {
                    MotionEvent.ACTION_DOWN -> {
                        Log.d("touch", "view pager item - ACTION_DOWN")
                    }
                    MotionEvent.ACTION_MOVE -> {
                        Log.d("touch", "view pager item - ACTION_MOVE")
                    }
                    MotionEvent.ACTION_UP -> {
                        Log.d("touch", "view pager item - ACTION_UP")
                    }
                }

                return false
            }
        })
    }

    fun bind(title: String) {
        view.tvTitle.text = title
    }

    fun changeBackgroundColor() {
        val random = Random
        val color: Int =
            Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256))
        view.clContainer.setBackgroundColor(color)
    }
}