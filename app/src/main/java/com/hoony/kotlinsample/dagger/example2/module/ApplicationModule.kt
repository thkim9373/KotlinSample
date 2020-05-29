package com.hoony.kotlinsample.dagger.example2.module

import android.app.Application
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule {
    // Context 타입의 의존성 객체 생성
    @Provides
    fun providesContext(application: Application): Application {
        return application
    }
}