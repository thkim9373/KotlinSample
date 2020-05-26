package com.hoony.kotlinsample.rx_java.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.hoony.kotlinsample.R
import com.hoony.kotlinsample.rx_java.example1.RxJavaExample1Activity

class RxJavaExamplesAdapter : RecyclerView.Adapter<ExampleViewHolder>() {

    private val targetNameList = arrayListOf(
        "Example1"
    )

    private val targetActivityList = arrayListOf<Class<*>>(
        RxJavaExample1Activity::class.java
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleViewHolder {
        return ExampleViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_single_text,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return targetActivityList.size
    }

    override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) {
        holder.setText(targetNameList[position])
        holder.setOnClickListener(targetActivityList[position])
    }
}