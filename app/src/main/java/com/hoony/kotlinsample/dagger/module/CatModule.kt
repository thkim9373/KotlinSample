package com.hoony.kotlinsample.dagger.module

import com.hoony.kotlinsample.dagger.data.Cat
import dagger.Module
import dagger.Provides

@Module
object CatModule {

    @Provides
    fun provideCat(): Cat {
        return Cat()
    }
}