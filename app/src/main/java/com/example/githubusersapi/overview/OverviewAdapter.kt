package com.example.githubusersapi.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.githubusersapi.data.Item
import com.example.githubusersapi.databinding.ItemOverviewBinding
import com.example.githubusersapi.databinding.ItemOverviewOthertypeBinding
import com.example.githubusersapi.util.TimeConverter

class OverviewAdapter(val viewModel: OverviewViewModel) :
        ListAdapter<DataItem, RecyclerView.ViewHolder>(DiffCallback){

    class DividerViewHolder(private var binding: ItemOverviewBinding)
        : RecyclerView.ViewHolder(binding.root) {
            fun bind(divider:DataItem.Divider, viewModel: OverviewViewModel){
                binding.divider = divider
                binding.viewModel = viewModel
                binding.executePendingBindings()
            }
            companion object {
                fun from(parent: ViewGroup): DividerViewHolder {
                    val layoutInflater = LayoutInflater.from(parent.context)
                    val binding = ItemOverviewBinding.inflate(layoutInflater, parent, false)
                    return DividerViewHolder(binding)
                }
            }
        }

    class NewsViewHolder(private var binding: ItemOverviewOthertypeBinding)
        : RecyclerView.ViewHolder(binding.root) {
            fun bind(news:DataItem.News, viewModel: OverviewViewModel){
                binding.news = news
                binding.tvCreateTime.text = TimeConverter.timestampToDate(news.news.extra?.created?.toLong() ?: 0L)
                binding.viewModel = viewModel
                binding.executePendingBindings()
            }
            companion object {
                fun from(parent: ViewGroup): NewsViewHolder {
                    val layoutInflater = LayoutInflater.from(parent.context)
                    val binding = ItemOverviewOthertypeBinding.inflate(layoutInflater, parent, false)
                    return NewsViewHolder(binding)
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

        private const val ITEM_VIEW_TYPE_DIVIDER = 0x00
        private const val ITEM_VIEW_TYPE_NEWS = 0x01
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType:Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_VIEW_TYPE_DIVIDER -> DividerViewHolder.from(parent)
            ITEM_VIEW_TYPE_NEWS -> NewsViewHolder.from(parent)
            else -> throw ClassCastException("Unknown viewType $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when(holder) {
            is DividerViewHolder -> {
                holder.bind(getItem(position) as DataItem.Divider, viewModel)
            }
            is NewsViewHolder -> {
                holder.bind(getItem(position) as DataItem.News, viewModel)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is DataItem.Divider -> ITEM_VIEW_TYPE_DIVIDER
            is DataItem.News -> ITEM_VIEW_TYPE_NEWS
        }
    }

}

sealed class DataItem {

    data class Divider(val news: Item): DataItem()

    data class News (val news: Item): DataItem()

}