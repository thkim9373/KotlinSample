package com.hoony.kotlinsample.detail

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hoony.kotlinsample.broadcast_receiver.AlarmTool
import com.hoony.kotlinsample.data.MemoDao
import com.hoony.kotlinsample.data.MemoData
import io.realm.Realm
import java.util.*

class DetailViewModel : ViewModel() {
    val title: MutableLiveData<String> = MutableLiveData<String>().apply { value = "" }
    val content: MutableLiveData<String> = MutableLiveData<String>().apply { value = "" }
    val alarmTime: MutableLiveData<Date> = MutableLiveData<Date>().apply { value = Date(0) }

    private var memoData = MemoData()

    private val realm: Realm by lazy {
        Realm.getDefaultInstance()
    }

    private val memoDao: MemoDao by lazy {
        MemoDao(realm)
    }

    fun loadMemo(id: String) {
        memoData = memoDao.selectMemo(id)
        title.value = memoData.title
        content.value = memoData.content
        alarmTime.value = memoData.alarmTime
    }

    fun addOrUpdateMemo(context: Context, title: String, content: String) {
        val alarmTimeValue = alarmTime.value!!
        memoDao.addOrUpdateMemo(memoData, title, content, alarmTimeValue)

        AlarmTool.deleteAlarm(context, memoData.id)

        if (alarmTimeValue.after(Date())) {
            AlarmTool.addAlarm(context, memoData.id, alarmTimeValue)
        }
    }

    fun deleteAlarm() {
        alarmTime.value = Date(0)
    }

    fun setAlarm(time: Date) {
        alarmTime.value = time
    }

    override fun onCleared() {
        super.onCleared()
        realm.close()
    }
}