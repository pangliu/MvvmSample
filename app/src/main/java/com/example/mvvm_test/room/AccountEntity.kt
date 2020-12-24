package com.example.mvvm_test.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "Account",
    indices = [Index(
        value = ["type", "name"],
        unique = true
    )]
)
data class AccountEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo val type: String,
    @ColumnInfo val name: String
)