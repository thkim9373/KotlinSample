package com.hoony.kotlinsample.dagger.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.hoony.kotlinsample.R
import com.hoony.kotlinsample.dagger.example1.DaggerActivity

class DaggerExampleListAdapter : RecyclerView.Adapter<DaggerExampleItemHolder>() {

    private val exampleList = arrayListOf("Simple example(Pet names)")
    private val targetActivityList = arrayListOf(DaggerActivity::class.java)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DaggerExampleItemHolder {
        return DaggerExampleItemHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_single_text,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return exampleList.size
    }

    override fun onBindViewHolder(holder: DaggerExampleItemHolder, position: Int) {
        holder.setText(exampleList[position])
        holder.setTargetActivity(targetActivityList[position])
    }
}