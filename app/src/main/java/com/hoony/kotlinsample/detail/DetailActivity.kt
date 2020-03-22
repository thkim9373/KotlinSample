package com.hoony.kotlinsample.detail

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.hoony.kotlinsample.R
import com.takisoft.datetimepicker.DatePickerDialog
import com.takisoft.datetimepicker.TimePickerDialog
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.content_detail.*
import java.util.*

class DetailActivity : AppCompatActivity() {

    private var viewModel: DetailViewModel? = null
    private val dialogCalendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(toolbar)

        viewModel = application!!.let {
            ViewModelProvider(viewModelStore, ViewModelProvider.AndroidViewModelFactory(it)).get(
                DetailViewModel::class.java
            )
        }

        viewModel!!.let {
            it.title.observe(this, Observer { supportActionBar?.title = it })
            it.content.observe(this, Observer { contentEdit.setText(it) })
            it.alarmTime.observe(this, Observer { alarmInfoView.setAlarmDate(it) })
        }

        val memoId = intent.getStringExtra("MEMO_ID")
        if (memoId != null) viewModel!!.loadMemo(memoId)

        toolbar_layout.setOnClickListener {
            val view = LayoutInflater.from(this).inflate(R.layout.dialog_title, null)
            val titleEdit = view.findViewById<EditText>(R.id.titleEdit)

            AlertDialog.Builder(this)
                .setTitle("제목을 입력하세요")
                .setView(view)
                .setNegativeButton("취소", null)
                .setPositiveButton("확인") { _, _ ->
                    supportActionBar?.title = titleEdit.text.toString()
                }
                .show()
        }

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_share -> {
                val intent = Intent()
                intent.action = Intent.ACTION_SEND
                intent.type = "text/plain"
                intent.putExtra(Intent.EXTRA_SUBJECT, supportActionBar?.title)
                intent.putExtra(Intent.EXTRA_TEXT, contentEdit.text.toString())

                startActivity(intent)
            }
            R.id.menu_alarm -> {
                if (viewModel?.alarmTime?.value!!.after(Date())) {
                    AlertDialog.Builder(this)
                        .setTitle(getString(R.string.notice))
                        .setMessage(getString(R.string.alarm_notice))
                        .setPositiveButton(
                            getString(R.string.update)
                        ) { _, _ ->
                            openDateDialog()
                        }
                        .setNegativeButton(getString(R.string.delete)) { _, _ ->
                            viewModel?.deleteAlarm()
                        }
                        .show()
                } else {
                    openDateDialog()
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun openDateDialog() {
        val datePickerDialog = DatePickerDialog(this)
        datePickerDialog.setOnDateSetListener { _, year, month, dayOfMonth ->
            dialogCalendar.set(year, month, dayOfMonth)
            openTimeDialog()
        }

        datePickerDialog.show()
    }

    private fun openTimeDialog() {
        val timePickerDialog = TimePickerDialog(
            this,
            TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                dialogCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
                dialogCalendar.set(Calendar.MINUTE, minute)

                viewModel?.setAlarm(dialogCalendar.time)
            },
            0, 0, false
        )
        timePickerDialog.show()
    }

    override fun onBackPressed() {
        super.onBackPressed()

        viewModel?.addOrUpdateMemo(
            applicationContext,
            supportActionBar?.title.toString(),
            contentEdit.text.toString()
        )
    }
}
