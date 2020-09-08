package com.example.initiumsolutionsassignment.main

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.initiumsolutionsassignment.R
import com.example.initiumsolutionsassignment.model.Entity
import kotlinx.android.synthetic.main.entity_item_layout.view.*

class EntityAdapter(private val addToCartClickListener: () -> Unit) :
    ListAdapter<Entity, EntityAdapter.ViewHolder>(DiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntityAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.entity_item_layout, parent, false))
    }

    override fun onBindViewHolder(holder: EntityAdapter.ViewHolder, position: Int) {
        holder.bind(getItem(position), addToCartClickListener)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Entity, addToCartClickListener: () -> Unit) {
            itemView.tv_entity_item.text = item.name
            itemView.img_entity_item.setImageBitmap(item.image)
            if (item.name == "Others") {
                itemView.img_entity_item.setOnClickListener {
                    Log.d("main", "bind: HERE3")
                    addToCartClickListener()
                }
            }
        }
    }

    class DiffCallBack : DiffUtil.ItemCallback<Entity>() {
        override fun areItemsTheSame(oldItem: Entity, newItem: Entity): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Entity, newItem: Entity): Boolean {
            return oldItem == newItem
        }
    }
}