package com.cloud.disk.bundle.dynamic

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cloud.dynamicbundle.R
import com.cloud.dynamicbundle.databinding.DynamicListItemBinding

class DynamicListAdapter() : ListAdapter<Dynamic, DynamicListAdapter.DynamicVH>(DynamicDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DynamicVH {
        return DynamicVH(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.dynamic_list_item, parent, false))
    }

    override fun onBindViewHolder(holder: DynamicVH, position: Int) {
        holder.binding.dynamic = getItem(position)
        holder.binding.executePendingBindings()
    }

    class DynamicVH(val binding: DynamicListItemBinding) : RecyclerView.ViewHolder(binding.root)

    private class DynamicDiffCallback : DiffUtil.ItemCallback<Dynamic>() {
        override fun areItemsTheSame(oldItem: Dynamic, newItem: Dynamic): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Dynamic, newItem: Dynamic): Boolean {
            return oldItem == newItem
        }
    }
}