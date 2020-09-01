package com.hoony.kotlinsample.notification

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.hoony.kotlinsample.R
import com.hoony.kotlinsample.databinding.FragmentBigTextBinding

class BigTextFragment : Fragment() {

    private lateinit var binding: FragmentBigTextBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_big_text, container, false)
        binding = DataBindingUtil.bind(view)!!
        return view
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.bigText.setText(
            "살어리 살어리랏다\n" +
                    "청산에 살어리랏다\n" +
                    "멀위랑 다래랑 먹고\n" +
                    "청산에 살어리랏다\n" +
                    "얄리얄리얄라셩얄라리얄라"
        )
    }

    fun getBigText(): String = binding.bigText.editableText.toString()
}