package com.hoony.kotlinsample.detail

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.hoony.kotlinsample.R
import com.hoony.kotlinsample.databinding.ActivityDetailBinding
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.content_detail.*

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private var viewModel: DetailViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(parent, R.layout.activity_detail)
        setSupportActionBar(toolbar)

        viewModel = application!!.let {
            ViewModelProvider(viewModelStore, ViewModelProvider.AndroidViewModelFactory(it)).get(
                DetailViewModel::class.java
            )
        }

        viewModel!!.let {
            it.title.observe(this, Observer { supportActionBar?.title = it })
            it.content.observe(this, Observer { contentEdit.setText(it) })
        }

        val memoId = intent.getStringExtra("MEMO_ID")
        if(memoId != null) viewModel!!.loadMemo(memoId)

        toolbar_layout.setOnClickListener {
            val view = LayoutInflater.from(this).inflate(R.layout.dialog_title, null)
            val titleEdit = view.findViewById<EditText>(R.id.titleEdit)

            AlertDialog.Builder(this)
                .setTitle("제목을 입력하세요")
                .setView(view)
                .setNegativeButton("취소", null)

        }

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }
}
