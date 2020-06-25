package com.hoony.kotlinsample.ui_example.view_pager_with_recycler_view

import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.hoony.kotlinsample.R
import com.hoony.kotlinsample.ui_example.view_pager_with_recycler_view.observable_recycler_view.ObservableScrollViewCallbacks
import com.hoony.kotlinsample.ui_example.view_pager_with_recycler_view.observable_recycler_view.ScrollState
import kotlinx.android.synthetic.main.activity_ui_view_pager_with_recycler_view.*

/**
 *  Nested scroll view example : https://black-jin0427.tistory.com/164
 */
class UIExampleActivity1 : AppCompatActivity(), ObservableScrollViewCallbacks {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ui_view_pager_with_recycler_view)

        initViewPager()
        initRecyclerView()
    }

    private fun initViewPager() {
        vpMain.apply {
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
            adapter = UIViewPagerAdapter()

            setOnTouchListener(object : View.OnTouchListener {
                override fun onTouch(v: View?, event: MotionEvent?): Boolean {

                    when (event?.action) {
                        MotionEvent.ACTION_DOWN -> {
                            Log.d("touch", "view pager - ACTION_DOWN")
                        }
                        MotionEvent.ACTION_MOVE -> {
                            Log.d("touch", "view pager - ACTION_MOVE")
                        }
                        MotionEvent.ACTION_UP -> {
                            Log.d("touch", "view pager - ACTION_UP")
                        }
                    }

                    return true
                }
            })
        }
    }

    private fun initRecyclerView() {
        rvList.apply {
            layoutManager =
                LinearLayoutManager(this@UIExampleActivity1, LinearLayoutManager.VERTICAL, false)
            addItemDecoration(
                DividerItemDecoration(
                    this@UIExampleActivity1,
                    DividerItemDecoration.VERTICAL
                )
            )
            adapter = RecyclerViewAdapter()

//            setOnTouchListener(object : View.OnTouchListener {
//                override fun onTouch(v: View?, event: MotionEvent?): Boolean {
//
//                    when(event?.action) {
//                        MotionEvent.ACTION_DOWN -> {
//                            Log.d("touch", "recycler view - ACTION_DOWN")
//                        }
//                        MotionEvent.ACTION_MOVE -> {
//                            Log.d("touch", "recycler view - ACTION_MOVE")
//                        }
//                        MotionEvent.ACTION_UP -> {
//                            Log.d("touch", "recycler view - ACTION_UP")
//                        }
//                    }
//
//                    return false
//                }
//            })

            addScrollViewCallbacks(this@UIExampleActivity1)
        }
    }

    override fun onUpOrCancelMotionEvent(scrollState: ScrollState?) {

    }

    override fun onScrollChanged(scrollY: Int, firstScroll: Boolean, dragging: Boolean) {

    }

    override fun onDownMotionEvent() {

    }
}