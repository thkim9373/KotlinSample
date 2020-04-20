package com.hoony.kotlinsample.room

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hoony.kotlinsample.room.db.table.user.User
import com.hoony.kotlinsample.room.repository.AppRepository
import com.hoony.kotlinsample.room.repository.tasks.GetAllUserListTask
import com.hoony.kotlinsample.room.repository.tasks.UserInsertTask

class RoomViewModel(application: Application) : AndroidViewModel(application),
    UserInsertTask.UserInsertTaskCallback, GetAllUserListTask.GetAllUserListTaskCallback {

    private val appRepository: AppRepository by lazy {
        AppRepository.getInstance(application)
    }

    init {
        getAllUserList()
    }

    //    var userListLiveData: LiveData<List<User>>? = null
    val userListLiveData: LiveData<List<User>> by lazy {
        appRepository.getUserList()
    }

    private val _toastMsgMutableLiveData = MutableLiveData<String>()
    val toastMsgLiveData: LiveData<String>
        get() = _toastMsgMutableLiveData

    private lateinit var name: String

    fun setName(name: String) {
        this.name = name
    }

    private fun getAllUserList() {
        appRepository.getAllUserList(this)
    }

    fun insertUser() {
        val user = User(this.name)
        appRepository.insertUser(user, this)
    }

    override fun onGetAllUserListTaskSuccess(userListLivaData: LiveData<List<User>>) {
//        this.userListLiveData = userListLivaData
    }

    override fun onGetAllUserListTaskFail(e: Exception) {
        this._toastMsgMutableLiveData.postValue("User list loading failed. Exception : $e")
    }

    override fun onUserInsertSuccess(user: User) {
        this._toastMsgMutableLiveData.postValue("User ${user.name} inserting completed.")
    }

    override fun onUserInsertFail(e: Exception) {
        this._toastMsgMutableLiveData.postValue("User inserting failed. Exception : $e")
    }
}