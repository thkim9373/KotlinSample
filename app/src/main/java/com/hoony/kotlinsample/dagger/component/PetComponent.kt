package com.hoony.kotlinsample.dagger.component

import com.hoony.kotlinsample.dagger.DaggerActivity
import com.hoony.kotlinsample.dagger.module.CatModule
import com.hoony.kotlinsample.dagger.module.DogModule
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