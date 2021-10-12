package com.notes.notes.lists.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.notes.notes.R

class ListViewHolder(
    itemView: View,
    private val clickListener: RecyclerOnClickListener
) : RecyclerView.ViewHolder(itemView),
    View.OnClickListener {

    private val textViewTitle: TextView = itemView.findViewById(R.id.textview_content_title)
    private val textViewBody: TextView = itemView.findViewById(R.id.textview_content_body)

    fun bindTitle(text: String?) {
        textViewTitle.text = text
    }

    fun bindBody(text: String?) {
        textViewBody.text = text
    }

    init {
        itemView.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        clickListener.onListClick(adapterPosition + 1)
    }

    companion object {
        fun create(parent: ViewGroup, clickListener: RecyclerOnClickListener): ListViewHolder {
            val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_viewholder_item, parent, false)
            return ListViewHolder(view, clickListener)
        }
    }
}
