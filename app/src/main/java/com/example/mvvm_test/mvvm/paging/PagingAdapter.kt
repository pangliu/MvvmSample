package com.example.mvvm_test.mvvm.paging

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm_test.R
import com.example.mvvm_test.room.AccountEntity
import kotlinx.android.synthetic.main.item_account.view.*

class PagingAdapter : PagedListAdapter<AccountEntity, PagingAdapter.ViewHolder>(
    diffCallback
) {

    companion object {
        val diffCallback = object: DiffUtil.ItemCallback<AccountEntity>() {
            override fun areItemsTheSame(
                oldItem: AccountEntity,
                newItem: AccountEntity
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: AccountEntity,
                newItem: AccountEntity
            ): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_account, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let {
            holder.binding(it)
        }
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun binding(item: AccountEntity) {
            itemView.tv_name.text = item.name
            itemView.tv_phone.text = item.phone
            itemView.tv_type.text = item.type
        }
    }
}