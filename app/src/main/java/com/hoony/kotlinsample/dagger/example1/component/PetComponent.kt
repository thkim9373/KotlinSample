package com.hoony.kotlinsample.dagger.example1.component

import com.hoony.kotlinsample.dagger.example1.DaggerActivity
import com.hoony.kotlinsample.dagger.example1.module.CatModule
import com.hoony.kotlinsample.dagger.example1.module.DogModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DogModule::class, CatModule::class])
interface PetComponent {

    fun inject(daggerActivity: DaggerActivity)

    @Component.Builder
    interface Builder {
        fun build(): PetComponent

        fun dogModule(dogModule: DogModule): Builder
        fun catModule(catModule: CatModule): Builder
    }
}