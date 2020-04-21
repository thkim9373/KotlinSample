package com.hoony.kotlinsample.room.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.hoony.kotlinsample.room.db.AppDataBase
import com.hoony.kotlinsample.room.db.table.user.User
import com.hoony.kotlinsample.room.db.table.user.UserDao
import com.hoony.kotlinsample.room.repository.tasks.UserInsertTask
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

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

    fun getUserList(): LiveData<List<User>> {
        return userDao.getAll()
    }

    private lateinit var appDataBase: AppDataBase
    private lateinit var userDao: UserDao

    suspend fun insertUser(user: User) {
        withContext(Dispatchers.IO) {
            userDao.insert(user)
        }
//        UserInsertTask(user, userDao, callback).execute()
    }

    suspend fun deleteAllUser() {
        withContext(Dispatchers.IO) {
            userDao.deleteAll()
        }
    }
}