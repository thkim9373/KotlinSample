package com.hoony.kotlinsample.room.db.table.base

import androidx.room.*

interface BaseDao<T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(t: T)

    @Delete
    fun delete(t: T)

    @Update
    fun update(t: T)
}