package com.example.githubusersapi.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.githubusersapi.data.User
import com.example.githubusersapi.databinding.ItemOverviewBinding

class OverviewAdapter(val viewModel: OverviewViewModel) :
        ListAdapter<User, OverviewAdapter.UserViewHolder>(DiffCallback){

    class UserViewHolder(private var binding: ItemOverviewBinding)
        : RecyclerView.ViewHolder(binding.root) {
            fun bind(user:User, viewModel: OverviewViewModel){
                binding.user = user
                binding.viewModel = viewModel
                binding.executePendingBindings()
            }
        }

    companion object DiffCallback : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return (oldItem.id == newItem.id)
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.nodeId == newItem.nodeId
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType:Int):UserViewHolder {
        return UserViewHolder(ItemOverviewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = getItem(position)
        holder.bind(getItem(position), viewModel)
    }

}