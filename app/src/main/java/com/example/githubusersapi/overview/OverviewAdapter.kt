package com.example.githubusersapi.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.githubusersapi.data.User
import com.example.githubusersapi.databinding.ItemOverviewBinding
import com.example.githubusersapi.databinding.ItemOverviewOthertypeBinding

class OverviewAdapter(val viewModel: OverviewViewModel) :
        ListAdapter<DataItem, RecyclerView.ViewHolder>(DiffCallback){

    class UserViewHolder(private var binding: ItemOverviewBinding)
        : RecyclerView.ViewHolder(binding.root) {
            fun bind(user:DataItem.Right, viewModel: OverviewViewModel){
                binding.right = user
                binding.viewModel = viewModel
                binding.executePendingBindings()
            }
            companion object {
                fun from(parent: ViewGroup): UserViewHolder {
                    val layoutInflater = LayoutInflater.from(parent.context)
                    val binding = ItemOverviewBinding.inflate(layoutInflater, parent, false)
                    return UserViewHolder(binding)
                }
            }
        }

    class UserOtherTypeViewHolder(private var binding: ItemOverviewOthertypeBinding)
        : RecyclerView.ViewHolder(binding.root) {
            fun bind(user:DataItem.Left, viewModel: OverviewViewModel){
                binding.left = user
                binding.viewModel = viewModel
                binding.executePendingBindings()
            }
            companion object {
                fun from(parent: ViewGroup): UserOtherTypeViewHolder {
                    val layoutInflater = LayoutInflater.from(parent.context)
                    val binding = ItemOverviewOthertypeBinding.inflate(layoutInflater, parent, false)
                    return UserOtherTypeViewHolder(binding)
                }
            }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<DataItem>() {
        override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
            return oldItem == newItem
        }

        private const val ITEM_VIEW_TYPE_ONE = 0x00
        private const val ITEM_VIEW_TYPE_TWO = 0x01
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType:Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_VIEW_TYPE_ONE -> UserViewHolder.from(parent)
            ITEM_VIEW_TYPE_TWO -> UserOtherTypeViewHolder.from(parent)
            else -> throw ClassCastException("Unknown viewType $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when(holder) {
            is UserViewHolder -> {
                holder.bind(getItem(position) as DataItem.Right, viewModel)
            }
            is UserOtherTypeViewHolder -> {
                holder.bind(getItem(position) as DataItem.Left, viewModel)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is DataItem.Right -> ITEM_VIEW_TYPE_ONE
            is DataItem.Left -> ITEM_VIEW_TYPE_TWO
        }
    }

}

sealed class DataItem {

    data class Right(val user: User): DataItem()

    data class Left(val user: User): DataItem()

}