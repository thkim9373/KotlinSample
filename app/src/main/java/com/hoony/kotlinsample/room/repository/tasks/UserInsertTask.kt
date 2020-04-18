package com.hoony.kotlinsample.room.repository.tasks

import android.os.Handler
import android.os.Looper
import com.hoony.kotlinsample.room.db.table.user.User
import com.hoony.kotlinsample.room.db.table.user.UserDao
import java.util.concurrent.Callable
import java.util.concurrent.Executors

class UserInsertTask(
    private val user: User,
    private val userDao: UserDao,
    private val callback: UserInsertTaskCallback
) : Callable<User> {

    private val handler = Handler(Looper.getMainLooper())
    private val executor = Executors.newSingleThreadExecutor()

    interface UserInsertTaskCallback {
        fun onUserInsertSuccess(user: User)
        fun onUserInsertFail(e: Exception)
    }

    override fun call(): User {
        userDao.insert(user)
        return user
    }

    fun execute() {
        executor.execute {
            try {
                val result = this.call()
                handler.post { callback.onUserInsertSuccess(result) }
            } catch (e: Exception) {
                handler.post { callback.onUserInsertFail(e) }
            }
        }
    }
}