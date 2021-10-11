package com.notes.notes.lists.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.notes.notes.database.ModelListItems
import com.notes.notes.lists.viewholder.ListViewHolder
import com.notes.notes.lists.viewholder.RecyclerOnClickListener

class RecyclerViewAdapter(val clickListener: RecyclerOnClickListener) :
    ListAdapter<ModelListItems, ListViewHolder>(WORDS_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder.create(parent, clickListener)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val current = getItem(position)
        holder.bindTitle(current.title)
        holder.bindBody(current.body)
    }

    companion object {
        private val WORDS_COMPARATOR = object : DiffUtil.ItemCallback<ModelListItems>() {
            override fun areItemsTheSame(
                oldItem: ModelListItems,
                newItem: ModelListItems
            ): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(
                oldItem: ModelListItems,
                newItem: ModelListItems
            ): Boolean {
                return oldItem.title == newItem.title
            }
        }
    }
}
