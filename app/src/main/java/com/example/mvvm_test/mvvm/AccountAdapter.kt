package com.example.mvvm_test.mvvm

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.mvvm_test.R
import com.example.mvvm_test.room.AccountEntity

class AccountAdapter(datas: MutableList<AccountEntity>):
    BaseQuickAdapter<AccountEntity, BaseViewHolder>(R.layout.item_account, datas) {


    override fun convert(holder: BaseViewHolder, item: AccountEntity) {
        holder.let {
            it.setText(R.id.tv_id, item.id.toString())
            it.setText(R.id.tv_name, item.name)
            it.setText(R.id.tv_type, item.type)
            it.setText(R.id.tv_phone, item.phone)
            it.setText(R.id.tv_photo, item.photo)
        }
    }
}