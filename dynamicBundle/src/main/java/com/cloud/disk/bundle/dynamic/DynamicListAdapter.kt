package com.cloud.disk.bundle.dynamic

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cloud.disk.bundle.dynamic.util.DateUtil.getDateToString
import com.cloud.dynamicbundle.R

class DynamicListAdapter(var infoList: List<Dynamic>, var context: Context) : RecyclerView.Adapter<DynamicListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.dynamic_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvName.text = infoList[position].content
        holder.tvSize.text = getDateToString(infoList[position].date)
    }

    override fun getItemCount(): Int {
        return infoList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvName: TextView = itemView.findViewById(R.id.tv_content)
        var tvSize: TextView = itemView.findViewById(R.id.tv_date)
    }


}