package com.hoony.kotlinsample.data

import io.realm.Realm
import io.realm.RealmResults
import io.realm.Sort

class MemoDao(private val realm: Realm) {

    fun getAllMemos(): RealmResults<MemoData> {
        return realm.where(MemoData::class.java)
            .sort("createAt", Sort.DESCENDING)
            .findAll()
    }

    fun selectMemo(id: String): MemoData {
        return realm.where(MemoData::class.java)
            .equalTo("id", id)
            .findFirst() as MemoData
    }
}