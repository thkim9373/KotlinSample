package com.hoony.kotlinsample.dagger.example1.module

import com.hoony.kotlinsample.dagger.example1.data.Dog
import dagger.Module
import dagger.Provides

@Module
object DogModule {

    @Provides
    fun provideDogModule(): Dog {
        return Dog()
    }
}