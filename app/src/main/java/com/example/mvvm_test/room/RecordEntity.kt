package com.example.mvvm_test.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(
    tableName = "Record",
    indices = [
        Index(value = ["date"])
    ]
)
data class RecordEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo val type: String,
    @ColumnInfo val money: Int,
    @ColumnInfo val memo: String? = null,
    @ColumnInfo val accountId: Int,
    @ColumnInfo val date: LocalDate,
)