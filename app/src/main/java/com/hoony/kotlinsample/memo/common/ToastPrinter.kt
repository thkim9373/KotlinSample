package com.hoony.kotlinsample.memo.common

import android.content.Context
import android.widget.Toast

public class ToastPrinter {
    private lateinit var mToast: Toast
    public fun show(context: Context, msg: String) {
        mToast.cancel()
        mToast = Toast.makeText(context, msg, Toast.LENGTH_SHORT)
        mToast.show()
    }
}