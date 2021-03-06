package com.hoony.kotlinsample.memo.list

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.hoony.kotlinsample.R
import com.hoony.kotlinsample.memo.detail.DetailActivity
import kotlinx.android.synthetic.main.activity_list.*

class ListActivity : AppCompatActivity() {

    private var viewModel: ListViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        setSupportActionBar(toolbar)

        val fragmentTransition = supportFragmentManager.beginTransaction()
        fragmentTransition.replace(R.id.contentLayout, MemoListFragment())
        fragmentTransition.commit()

        viewModel = application!!.let {
            ViewModelProvider(viewModelStore, ViewModelProvider.AndroidViewModelFactory(it)).get(
                ListViewModel::class.java
            )
        }

        fab.setOnClickListener {
            val intent = Intent(applicationContext, DetailActivity::class.java)
            startActivity(intent)
        }
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

}
