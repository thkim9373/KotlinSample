package com.hoony.kotlinsample.dagger.example1.module

import com.hoony.kotlinsample.dagger.example1.data.Cat
import dagger.Module
import dagger.Provides

@Module
object CatModule {

    @Provides
    fun provideCat(): Cat {
        return Cat()
    }
}