package com.hoony.kotlinsample.custom_view.radio_group

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import androidx.annotation.IdRes
import androidx.core.view.ViewCompat

class CustomRadioGroup @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleRes: Int = 0
) : LinearLayout(context, attrs, defStyleRes) {

    private val passThroughHierarchyChangeListener: PassThroughHierarchyChangeListener

    init {
        passThroughHierarchyChangeListener = PassThroughHierarchyChangeListener()
        super.setOnHierarchyChangeListener(passThroughHierarchyChangeListener)
    }

    interface OnCheckedChangeListener {
        fun onChecked(@IdRes id: Int)
    }

    private val listener: OnCheckedChangeListener? = null

    /**
     *ㅈ
     * A pass-through listener acts upon the events and dispatches them
     * to another listener. This allows the table layout to set its own internal
     * hierarchy change listener without preventing the user to setup his.
     */
    private inner class PassThroughHierarchyChangeListener : OnHierarchyChangeListener {
        private val onHierarchyChangeListener: OnHierarchyChangeListener? = null

        /**
         * {@inheritDoc}
         */
        override fun onChildViewAdded(parent: View, child: View) {
            if (parent === this@CustomRadioGroup && child is CustomRadioButton) {
                var id = child.getId()
                // generates an id if it's missing
                if (id == NO_ID) {
                    id = ViewCompat.generateViewId()
                    child.setId(id)
                }
                child.listener =
                    CustomRadioButton.OnCheckChangedListener { isChecked, radioButtonId ->
                        if (isChecked) listener?.onChecked(radioButtonId)
                    }
            }
            onHierarchyChangeListener?.onChildViewAdded(parent, child)
        }

        /**
         * {@inheritDoc}
         */
        override fun onChildViewRemoved(parent: View, child: View) {
            if (parent === this@CustomRadioGroup && child is CustomRadioButton) {
                child.listener = null
            }
            onHierarchyChangeListener?.onChildViewRemoved(parent, child)
        }
    }
}