package com.hoony.kotlinsample.list

import androidx.lifecycle.ViewModel
import com.hoony.kotlinsample.data.MemoDao
import com.hoony.kotlinsample.data.MemoData
import com.hoony.kotlinsample.data.RealmLiveData
import io.realm.Realm

class ListViewModel : ViewModel() {
    private val realm: Realm by lazy {
        Realm.getDefaultInstance()
    }

    private val memoDao: MemoDao by lazy {
        MemoDao(realm)
    }

    val memoLiveData: RealmLiveData<MemoData> by lazy {
        RealmLiveData(memoDao.getAllMemos())
    }

    override fun onCleared() {
        super.onCleared()
        realm.close()
    }
}