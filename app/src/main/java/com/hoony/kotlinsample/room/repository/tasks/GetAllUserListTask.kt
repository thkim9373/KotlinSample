package com.hoony.kotlinsample.room.repository.tasks

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import com.hoony.kotlinsample.room.db.table.user.User
import com.hoony.kotlinsample.room.db.table.user.UserDao
import java.util.concurrent.Callable
import java.util.concurrent.Executors

class GetAllUserListTask(
    private val userDao: UserDao,
    private val callback: GetAllUserListTaskCallback
) : Callable<LiveData<List<User>>> {

    private val handler = Handler(Looper.getMainLooper())
    private val executor = Executors.newSingleThreadExecutor()

    interface GetAllUserListTaskCallback {
        fun onGetAllUserListTaskSuccess(userListLivaData: LiveData<List<User>>)
        fun onGetAllUserListTaskFail(e: Exception)
    }

    override fun call(): LiveData<List<User>> {
        return userDao.getAll()
    }

    fun execute() {
        executor.execute {
            try {
                val result = this.call()
                handler.post { callback.onGetAllUserListTaskSuccess(result) }
            } catch (e: Exception) {
                handler.post { callback.onGetAllUserListTaskFail(e) }
            }
        }
    }
}