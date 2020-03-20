package com.hoony.kotlinsample.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hoony.kotlinsample.data.MemoDao
import com.hoony.kotlinsample.data.MemoData
import io.realm.Realm

class DetailViewModel : ViewModel() {
    val title: MutableLiveData<String> = MutableLiveData<String>().apply { value = "" }
    val content: MutableLiveData<String> = MutableLiveData<String>().apply { value = "" }

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
    }

    fun addOrUpdateMemo(title: String, content: String) {
        memoDao.addOrUpdateMemo(memoData, title, content)
    }

    override fun onCleared() {
        super.onCleared()
        realm.close()
    }
}