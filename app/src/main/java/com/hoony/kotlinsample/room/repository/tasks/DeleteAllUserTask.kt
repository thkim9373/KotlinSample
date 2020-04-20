package com.hoony.kotlinsample.room.repository.tasks

import android.os.Handler
import android.os.Looper
import com.hoony.kotlinsample.room.db.table.user.UserDao
import java.util.concurrent.Callable
import java.util.concurrent.Executors

class DeleteAllUserTask(
    private val userDao: UserDao,
    private val callback: DeleteAllUserTaskCallback
) : Callable<Boolean> {

    private val handler = Handler(Looper.getMainLooper())
    private val executor = Executors.newSingleThreadExecutor()

    interface DeleteAllUserTaskCallback {
        fun onDeleteAllUserTaskSuccess(isComplete: Boolean)
        fun onDeleteAllUserTaskFail(e: Exception)
    }

    override fun call(): Boolean {
        userDao.deleteAll()
        return true
    }

    fun execute() {
        executor.execute {
            try {
                val result = this.call()
                handler.post { callback.onDeleteAllUserTaskSuccess(result) }
            } catch (e: Exception) {
                handler.post { callback.onDeleteAllUserTaskFail(e) }
            }
        }
    }
}