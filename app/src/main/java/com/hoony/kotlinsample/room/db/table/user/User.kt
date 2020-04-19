package com.hoony.kotlinsample.room.db.table.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
class User(
    @PrimaryKey(autoGenerate = true) var id: Int,
    @ColumnInfo(name = "name") var name: String
) {
    constructor(name: String) : this(1, name)
}