package com.hoony.kotlinsample.rx_java.example1


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.hoony.kotlinsample.R
import com.hoony.kotlinsample.databinding.ActivityRxJavaExample1Binding
import rx.Observable
import rx.Subscriber

class RxJavaExample1Activity : AppCompatActivity() {

    private lateinit var binding: ActivityRxJavaExample1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_rx_java_example_1)

        exportHelloWorld1()
        exportHelloWorld2()
        exportHelloWorldWithNameTag()
        exportHelloWorldWithMapOnMap()
    }

    private fun exportHelloWorld1() {
        val observable = Observable.create<String> {
            it.onNext("Example1 : Hello world!")
            it.onCompleted()
        }

        val subscriber = object : Subscriber<String>() {
            override fun onNext(t: String?) {
                binding.tv1.text = t
            }

            override fun onCompleted() {
            }

            override fun onError(e: Throwable?) {
            }
        }

        observable.subscribe(subscriber)
    }

    private fun exportHelloWorld2() {
        Observable.just("Example2 : Hello world!!!")
            .subscribe {
                binding.tv2.text = it
            }
    }

    private fun exportHelloWorldWithNameTag() {
        Observable.just("Example3 : Hello world!")
            .map {
                "$it -isaac"
            }
            .subscribe {
                binding.tv3.text = it
            }
    }

    private fun exportHelloWorldWithMapOnMap() {
        Observable.just("Example4 : Hello world!!!")
            .map {
                "$it -isaac"
            }
            .map {
                it.hashCode()
            }
            .map {
                it.toString()
            }
            .subscribe {
                binding.tv4.text = it
            }
    }
}