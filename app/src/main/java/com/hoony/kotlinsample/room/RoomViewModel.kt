package com.hoony.kotlinsample.room

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.hoony.kotlinsample.room.db.table.user.User
import com.hoony.kotlinsample.room.repository.AppRepository
import com.hoony.kotlinsample.room.repository.tasks.GetAllUserListTask
import com.hoony.kotlinsample.room.repository.tasks.UserInsertTask

class RoomViewModel(application: Application) : AndroidViewModel(application),
    UserInsertTask.UserInsertTaskCallback, GetAllUserListTask.GetAllUserListTaskCallback {

    private val appRepository = AppRepository.getInstance(application)

    private lateinit var userListLiveData: LiveData<List<User>>

    fun getAllUserList() {
        appRepository.getAllUserList(this)
    }

    override fun onGetAllUserListTaskSuccess(userListLivaData: LiveData<List<User>>) {
        this.userListLiveData = userListLivaData
    }

    override fun onGetAllUserListTaskFail(e: Exception) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun insertUser(user: User) {
        appRepository.insertUser(user, this)
    }

    override fun onUserInsertSuccess(user: User) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onUserInsertFail(e: Exception) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}