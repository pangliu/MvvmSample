package com.example.mvvm_test.mvvm.paging

import androidx.paging.ItemKeyedDataSource
import com.example.mvvm_test.room.AccountEntity

/**
 * 使用時機：
 * 當 RecyclerView 資料的 Key 有連續性，可根據資料的 Key 找到上一筆或下一筆資料
 */
class MyItemKeyedDataSource: ItemKeyedDataSource<Int, AccountEntity>() {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<AccountEntity>
    ) {
        // Key -> 查詢的索引
        // Size -> 要求資料的長度
        val initkey = params.requestedInitialKey ?: 0
        val size = params.requestedLoadSize
        // 從 repository 取的 AccountEntity 資料
        TODO("Not yet implemented")
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<AccountEntity>) {
        // Key -> 查詢的索引，這裡給的是上一頁最後一筆資料的Key
        // Size -> 要求資料的長度
        TODO("Not yet implemented")
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<AccountEntity>) {
        // Key -> 查詢的索引，這裡給的是上一頁第一筆資料的Key
        // Size -> 要求資料的長度
        TODO("Not yet implemented")
    }

    override fun getKey(item: AccountEntity): Int = item.id!!
}