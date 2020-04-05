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
    var memoData = MemoData()
    val memoLiveData: MutableLiveData<MemoData> by lazy {
        MutableLiveData<MemoData>().apply { value = memoData }
    }

    private val realm: Realm by lazy {
        Realm.getDefaultInstance()
    }

    private val memoDao: MemoDao by lazy {
        MemoDao(realm)
    }

    fun loadMemo(id: String) {
        memoData = realm.copyFromRealm(memoDao.selectMemo(id))
        memoLiveData.value = memoData
    }

    fun addOrUpdateMemo(context: Context) {
        memoDao.addOrUpdateMemo(memoData)

        AlarmTool.deleteAlarm(context, memoData.id)

        if (memoData.alarmTime.after(Date())) {
            AlarmTool.addAlarm(context, memoData.id, memoData.alarmTime)
        }
    }

    fun deleteAlarm() {
        memoData.alarmTime = Date(0)
        memoLiveData.value = memoData
    }

    fun setAlarm(time: Date) {
        memoData.alarmTime = time
        memoLiveData.value = memoData
    }

    fun deleteLocation() {
        memoData.latitude = 0.0
        memoData.longitude = 0.0
        memoLiveData.value = memoData
    }

    fun setLocation(latitude: Double, longitude: Double) {
        memoData.latitude = latitude
        memoData.longitude = longitude
        memoLiveData.value = memoData
    }

    override fun onCleared() {
        super.onCleared()
        realm.close()
    }
}