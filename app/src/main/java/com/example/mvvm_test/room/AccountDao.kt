package com.example.mvvm_test.room

import android.accounts.Account
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AccountDao {

    @Query("SELECT * FROM Account where type = :type")
    suspend fun getAccount(type: String): List<AccountEntity>

    @Query("SELECT * FROM Account")
    suspend fun getAllAccount(): List<AccountEntity>

    // 當衝突時不寫入
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertAccount(account: AccountEntity): Long
}