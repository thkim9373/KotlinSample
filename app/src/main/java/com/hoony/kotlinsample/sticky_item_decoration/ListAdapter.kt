package com.hoony.kotlinsample.sticky_item_decoration

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hoony.kotlinsample.R

class ListAdapter(
    private val wordList: List<String>,
    private val alphabetMap: Map<Int, String>
) : RecyclerView.Adapter<Item>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Item {
        return Item(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_sticky,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return wordList.size
    }

    override fun onBindViewHolder(holder: Item, position: Int) {
        holder.setAlphabet(getAlphabet(position))
        holder.setText(wordList[position])
    }

    private fun getAlphabet(position: Int): String {
        var alphabet: String = ""
        for (entry in alphabetMap.entries) {
            if(position >= entry.key) {
                alphabet = entry.value
            }
            if(position <= entry.key) {
                return entry.value
            }
        }
        return alphabet
    }
}