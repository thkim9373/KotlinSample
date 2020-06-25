package com.hoony.kotlinsample.ui_example.view_pager_with_recycler_view

import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.hoony.kotlinsample.databinding.ItemSingleTextBinding

class RecyclerVIewItemViewHolder(private val binding: ItemSingleTextBinding) :
    RecyclerView.ViewHolder(binding.root) {

    init {
        binding.root.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {

                when(event?.action) {
                    MotionEvent.ACTION_DOWN -> {
                        Log.d("touch", "list Item - ACTION_DOWN")
                    }
                    MotionEvent.ACTION_MOVE -> {
                        Log.d("touch", "list Item - ACTION_MOVE")
                    }
                    MotionEvent.ACTION_UP -> {
                        Log.d("touch", "list Item - ACTION_UP")
                    }
                }

                return false
            }
        })
    }

    fun bind(text: String) {
        binding.tvTitle.text = text
//        itemView.setOnTouchListener { v, event ->
//            (v.context as? UIActivity1)?.viewPager()?.dispatchTouchEvent(event)
//            false
//        }
    }


}