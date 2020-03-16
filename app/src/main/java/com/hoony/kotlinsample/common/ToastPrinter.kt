package com.hoony.kotlinsample.common

import android.content.Context
import android.widget.Toast

public class ToastPrinter {
    private lateinit var toast: Toast
    public fun show(context: Context, msg: String) {
        toast.cancel()
        toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT)
        toast.show()
    }
}