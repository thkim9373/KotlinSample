package com.hoony.kotlinsample.common

import android.content.Context
import android.widget.Toast

class ToastPrinter {
    companion object {
        private var toast: Toast? = null
        fun showToast(context: Context, msg: String) {
            toast?.cancel()
            toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT)
            toast?.show()
        }
    }
}