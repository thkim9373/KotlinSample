package com.hoony.kotlinsample.sticky_item_decoration

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_sticky.view.*

class Item(private val view: View) : RecyclerView.ViewHolder(view) {

    fun setAlphabet(alphabet: String) {
        view.tvAlphabet.text = alphabet
    }

    fun setText(text: String) {
        view.tvWord.text = text
    }
}