package com.hoony.kotlinsample.behavior.floating_button_example

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import com.google.android.material.snackbar.Snackbar
import com.hoony.kotlinsample.R
import kotlinx.android.synthetic.main.activity_behavior_1.*

class BehaviorActivity1 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_behavior_1)

        fab1.setOnClickListener {
            Snackbar.make(it, "Hello World!!!", Snackbar.LENGTH_LONG).show()
        }
        fab2.setOnClickListener {
            Snackbar.make(it, "Hello World!!!", Snackbar.LENGTH_LONG).show()
        }
    }
}