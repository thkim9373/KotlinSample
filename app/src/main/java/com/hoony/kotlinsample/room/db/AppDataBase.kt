package com.hoony.kotlinsample.room.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hoony.kotlinsample.room.db.table.user.User
import com.hoony.kotlinsample.room.db.table.user.UserDao

@Database(entities = [User::class], version = 1)
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
            return Room.databaseBuilder(
                context.applicationContext,
                AppDataBase::class.java,
                "user_db"
            )
                .build()
        }
    }

    abstract fun userDao(): UserDao
}