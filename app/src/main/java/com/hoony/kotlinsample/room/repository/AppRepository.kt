package com.hoony.kotlinsample.room.repository

import android.content.Context
import com.hoony.kotlinsample.room.db.AppDataBase
import com.hoony.kotlinsample.room.db.table.user.User
import com.hoony.kotlinsample.room.db.table.user.UserDao
import com.hoony.kotlinsample.room.repository.tasks.GetAllUserListTask
import com.hoony.kotlinsample.room.repository.tasks.UserInsertTask

class AppRepository {

    companion object {
        private var instance: AppRepository? = null

        fun getInstance(context: Context): AppRepository {
            if (instance == null) {
                instance = createAppRepository(context)
            }
            return instance as AppRepository
        }

        private fun createAppRepository(context: Context): AppRepository {
            val appRepository = AppRepository()
            appRepository.let {
                it.appDataBase = AppDataBase.getInstance(context)
                it.userDao = it.appDataBase.userDao()
            }
            return appRepository
        }
    }

    private lateinit var appDataBase: AppDataBase
    private lateinit var userDao: UserDao

    fun getAllUserList(callback: GetAllUserListTask.GetAllUserListTaskCallback) {
        GetAllUserListTask(userDao, callback).execute()
    }

    fun insertUser(user: User, callback: UserInsertTask.UserInsertTaskCallback) {
        UserInsertTask(user, userDao, callback).execute()
    }
}