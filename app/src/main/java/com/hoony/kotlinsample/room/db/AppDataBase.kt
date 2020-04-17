package com.hoony.kotlinsample.room.db

import android.app.Application
import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

abstract class AppDataBase : RoomDatabase() {
    companion object {
        private var INSTANCE: AppDataBase? = null

        fun getInstance(application: Application): AppDataBase {
            if (INSTANCE == null) {
                synchronized(AppDataBase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = buildAppDatabase(application)
                    }
                }
            }
            return INSTANCE!!
        }

        fun buildAppDatabase(context: Context): AppDataBase {
            return Room.databaseBuilder(context, AppDataBase::class.java, "user_db")
                .build()
        }
    }
}