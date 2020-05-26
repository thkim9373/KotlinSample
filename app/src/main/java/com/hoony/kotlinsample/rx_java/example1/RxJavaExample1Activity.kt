package com.hoony.kotlinsample.rx_java.example1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.hoony.kotlinsample.R
import com.hoony.kotlinsample.databinding.ActivityRxJavaExample1Binding
import rx.Observable
import rx.Subscriber

class RxJavaExample1Activity : AppCompatActivity() {

    private lateinit var binding : ActivityRxJavaExample1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_rx_java_example_1)

        val observable = Observable.create<String> {
            it.onNext("Hello world!")
            it.onCompleted()
        }

        val subscriber = object : Subscriber<String>() {
            override fun onNext(t: String?) {
                binding.tv.text = t
            }

            override fun onCompleted() {
                TODO("Not yet implemented")
            }

            override fun onError(e: Throwable?) {
                TODO("Not yet implemented")
            }
        }

        observable.subscribe(subscriber)
    }
}