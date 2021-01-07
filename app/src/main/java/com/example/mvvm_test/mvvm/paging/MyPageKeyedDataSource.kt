package com.example.mvvm_test.mvvm.paging

import androidx.paging.PageKeyedDataSource
import com.example.mvvm_test.room.AccountEntity

/**
 * 使用時機：
 * 原始資料來源（遠端 Server 或其他資料來源）已有分頁功能
 * 根據每頁的 Key 取得 RecyclerView 的 Item 資料
 */
class MyPageKeyedDataSource: PageKeyedDataSource<Int, AccountEntity>() {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, AccountEntity>
    ) {
        // params中沒有預設的Key
        val size = params.requestedLoadSize
        /***
         * TODO
         * 1.從 repository 取得 item
         * 2.指定 上一頁 和 下一頁 的Key
         */
//        callback.onResult(items, items[0].id, items.last().id)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, AccountEntity>) {
        val key = params.key
        val size = params.requestedLoadSize
        /***
         * TODO
         * 1.從 repository 取得 item
         * 2.指定下一頁 的Key
         */
//        callback.onResult(items, items.last().id)
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, AccountEntity>) {
        val key = params.key
        val size = params.requestedLoadSize
        /***
         * TODO
         * 1.從 repository 取得 item
         * 2.指定上一頁 的Key
         */
//        callback.onResult(items, items.first().id)
    }
}