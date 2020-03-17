package com.hoony.kotlinsample.List

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hoony.kotlinsample.R
import com.hoony.kotlinsample.detail.DetailActivity
import kotlinx.android.synthetic.main.activity_list.*

class ListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            val intent = Intent(applicationContext, DetailActivity::class.java)
            startActivity(intent)
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

}
