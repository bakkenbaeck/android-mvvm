package com.bakkenbaeck.mvvm.ui.comments

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bakkenbaeck.mvvm.R
import com.bakkenbaeck.mvvm.model.data.Comment


class CommentsAdapter(
        private val itemClick: (Int) -> Unit
) : RecyclerView.Adapter<CommentViewHolder>() {

    private val items: MutableList<Comment> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CommentViewHolder(layoutInflater.inflate(R.layout.list_item_comment, parent, false))
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        val item = getItemForPosition(position)
        holder.body.text = item.body
        holder.body.setOnClickListener { itemClick(item.id) }
    }

    private fun getItemForPosition(position: Int) = items[position]

    override fun getItemCount() = items.size

    fun add(comments: List<Comment>) {
        items.clear()
        items.addAll(comments)
        notifyDataSetChanged()
    }
}