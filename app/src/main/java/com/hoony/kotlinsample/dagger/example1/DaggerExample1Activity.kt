package com.hoony.kotlinsample.dagger.example1

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.hoony.kotlinsample.R
import com.hoony.kotlinsample.dagger.example1.component.DaggerPetComponent
import com.hoony.kotlinsample.dagger.example1.data.Cat
import com.hoony.kotlinsample.dagger.example1.data.Dog
import com.hoony.kotlinsample.dagger.example1.module.CatModule
import com.hoony.kotlinsample.dagger.example1.module.DogModule
import com.hoony.kotlinsample.databinding.ActivityDaggerExample1Binding
import javax.inject.Inject

// This example from : https://medium.com/@dlgksah/dagger2-kotlin-example-4c90d3d56edc
class DaggerExample1Activity : AppCompatActivity() {
    private lateinit var binding: ActivityDaggerExample1Binding

    // 어노테이션을 사용하여 의존성 객체를 주입한다.
    @Inject
    lateinit var cat: Cat

    @Inject
    lateinit var dog: Dog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_dagger_example_1)

        injectComponent()

        setListener()
    }

    private fun injectComponent() {
        DaggerPetComponent.builder()
            .catModule(CatModule)
            .dogModule(DogModule)
            .build()
            .inject(this)
    }

    @SuppressLint("SetTextI18n")
    private fun setListener() {
        binding.btCat.setOnClickListener {
            binding.tvPetName.text = "Cat name : " + cat.getCatName()
        }
        binding.btDog.setOnClickListener {
            binding.tvPetName.text = "Dog name : " + dog.getDogName()
        }
    }
}