package com.example.mvvm_test.mvvm.paging

import androidx.paging.PositionalDataSource
import com.example.mvvm_test.room.AccountEntity

class MyPositionalDataSource: PositionalDataSource<AccountEntity>() {

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<AccountEntity>) {
        TODO("Not yet implemented")
    }

    override fun loadInitial(
        params: LoadInitialParams,
        callback: LoadInitialCallback<AccountEntity>
    ) {
        TODO("Not yet implemented")
    }
}