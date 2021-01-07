package com.example.mvvm_test.mvvm.paging

import androidx.paging.DataSource
import com.example.mvvm_test.room.AccountEntity

class DataSourceFactory(private val dataSourceType: Int): DataSource.Factory<Int, AccountEntity>() {

    companion object {
        const val ITEM_KEYED = 0
        const val PAGE_KEYED = 1
        const val POSITIONAL = 2

        fun getDataSource(which: Int): DataSource<Int, AccountEntity> {
            return when(which) {
                ITEM_KEYED -> MyItemKeyedDataSource()
                PAGE_KEYED -> MyPageKeyedDataSource()
                POSITIONAL -> MyPositionalDataSource()
                else -> throw IllegalArgumentException("unknow data source")
            }
        }
    }

    override fun create(): DataSource<Int, AccountEntity> {
        return getDataSource(dataSourceType)
    }
}