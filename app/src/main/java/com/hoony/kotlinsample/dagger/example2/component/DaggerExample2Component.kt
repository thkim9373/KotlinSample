package com.hoony.kotlinsample.dagger.example2.component

import com.hoony.kotlinsample.dagger.example2.DaggerExample2Activity
import com.hoony.kotlinsample.dagger.example2.annotation.ActivityScope
import dagger.Subcomponent
import dagger.android.AndroidInjector

@ActivityScope
@Subcomponent
interface DaggerExample2Component : AndroidInjector<DaggerExample2Activity> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<DaggerExample2Activity>()
}