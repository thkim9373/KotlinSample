package com.hoony.kotlinsample.dagger.module

import com.hoony.kotlinsample.dagger.data.Dog
import dagger.Module
import dagger.Provides

@Module
object DogModule {

    @Provides
    fun provideDogModule(): Dog {
        return Dog()
    }
}