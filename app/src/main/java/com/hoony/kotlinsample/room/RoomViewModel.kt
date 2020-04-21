package com.hoony.kotlinsample.room

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.hoony.kotlinsample.room.db.table.user.User
import com.hoony.kotlinsample.room.repository.AppRepository
import kotlinx.coroutines.launch

class RoomViewModel(application: Application) : AndroidViewModel(application) {

    private val appRepository: AppRepository by lazy {
        AppRepository.getInstance(application)
    }

    //    val userListLiveData: LiveData<List<User>> = liveData {
//        appRepository.getUserList()
//    }

    val userListLiveData: LiveData<List<User>> by lazy {
        appRepository.getUserList()
    }

    private val _toastMsgMutableLiveData = MutableLiveData<String>()
    val toastMsgLiveData: LiveData<String>
        get() = _toastMsgMutableLiveData

    var nameField = ObservableField<String>()

    fun insertUser() {
        viewModelScope.launch {
            try {
                val name = nameField.get()
                if (name != null && name != "") {
                    val user = User(name)
                    appRepository.insertUser(user)

                    _toastMsgMutableLiveData.postValue("User ${user.name} inserting completed.")
                } else {
                    throw NullPointerException("User name is null.")
                }
                nameField.set(null)
            } catch (e: Exception) {
                _toastMsgMutableLiveData.postValue("User inserting failed. \nException : $e")
            }
        }
    }

    fun deleteAllUser() {
        viewModelScope.launch {
            appRepository.deleteAllUser()
        }
    }
}