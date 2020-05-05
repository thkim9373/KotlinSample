package com.hoony.kotlinsample.content_provider.video.list

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.hoony.kotlinsample.R
import com.hoony.kotlinsample.content_provider.video.VideoViewModel
import com.hoony.kotlinsample.databinding.ActivityVideoListBinding

class VideoActivity : AppCompatActivity() {

    private val permissions = arrayOf(
        android.Manifest.permission.READ_EXTERNAL_STORAGE
    )

    private lateinit var binding: ActivityVideoListBinding
    private lateinit var viewModel: VideoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (!hasPermissions()) return

        createView()
    }

    private fun hasPermissions(): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            for (permission in permissions) {
                if (checkSelfPermission(permission) == PackageManager.PERMISSION_DENIED) {
                    requestPermissions(permissions, 0)
                    return false
                }
            }
        }
        return true
    }

    private fun createView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_video_list)
        viewModel =
            ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(application)).get(
                VideoViewModel::class.java
            )

        setView()
        setObserve()
    }

    private fun setView() {
        binding.rvList.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvList.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )
    }

    private fun setObserve() {
        viewModel.videoListLiveData.observe(
            this,
            Observer {
                binding.rvList.adapter =
                    VideoAdapter(
                        it
                    )
            }
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 0) {
            for (result in grantResults) {
                if (result == PackageManager.PERMISSION_DENIED) {
                    AlertDialog.Builder(this)
                        .setTitle(getString(R.string.permission_dialog_title))
                        .setMessage(getString(R.string.permission_dialog_message))
                        .setPositiveButton(getString(R.string.setting)) { _, _ ->
                            val intent = Intent(
                                Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                                Uri.fromParts("package", packageName, null)
                            )
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                            startActivity(intent)
                        }
                        .setNegativeButton(getString(R.string.cancel)) { _, _ ->
                            finish()
                        }
                        .show()
                    return
                }
            }
        }

        createView()
    }
}
