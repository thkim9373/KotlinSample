package com.hoony.kotlinsample.ui_example.view_pager_in_recycler_view

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class ItemDecoration(context:Context) : RecyclerView.ItemDecoration() {

    private val topPadding: Int = com.hoony.kotlinsample.util.ViewUtil.convertDpToPixel(context, 80f).toInt()

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
//        super.getItemOffsets(outRect, view, parent, state)

        val position = parent.getChildAdapterPosition(view)
        outRect.bottom = -topPadding
//        if (position == 0) {
//            outRect.setEmpty()
//        } else {
//            outRect.top = -topPadding
//        }
    }

    //    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
//        for(i in 0..parent.childCount) {
//            val view = parent.getChildAt(i)
//            if(view != null) {
//                val position = parent.getChildAdapterPosition(view)
//                val viewType = parent.adapter?.getItemViewType(position)
//                if(viewType != RecyclerViewAdapter.ITEM_VIEW_PAGER) {
//                    c.drawRect(view.left.toFloat(),
//                        view.bottom.toFloat(), view.right.toFloat(), (view.top - 0.5).toFloat(), Paint()
//                    )
//                }
//            }
//        }
//    }
}