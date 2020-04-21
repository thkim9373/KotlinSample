package com.hoony.kotlinsample.room.db.table.user

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.hoony.kotlinsample.room.db.table.base.BaseDao

@Dao
interface UserDao : BaseDao<User> {
    @Query("select * from user as u order by u.id desc")
    fun getAll(): LiveData<List<User>>

    @Query("DELETE FROM user")
    fun deleteAll()
}