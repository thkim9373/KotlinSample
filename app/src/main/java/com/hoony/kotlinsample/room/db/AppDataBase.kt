package com.hoony.kotlinsample.room.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hoony.kotlinsample.room.db.table.user.UserDao

abstract class AppDataBase : RoomDatabase() {
    companion object {
        private var INSTANCE: AppDataBase? = null

        fun getInstance(context: Context): AppDataBase {
            if (INSTANCE == null) {
                synchronized(AppDataBase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = buildAppDatabase(context)
                    }
                }
            }
            return INSTANCE!!
        }

        private fun buildAppDatabase(context: Context): AppDataBase {
            return Room.databaseBuilder(context, AppDataBase::class.java, "user_db")
                .build()
        }
    }

    abstract val userDao: UserDao
}