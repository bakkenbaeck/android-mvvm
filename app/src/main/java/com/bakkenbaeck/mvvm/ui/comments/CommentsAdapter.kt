package com.bakkenbaeck.mvvm.ui.comments

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bakkenbaeck.mvvm.R
import com.bakkenbaeck.mvvm.model.data.Comment


class CommentsAdapter : RecyclerView.Adapter<CommentViewHolder>() {

    private val items: MutableList<Comment> = mutableListOf()

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CommentViewHolder(layoutInflater.inflate(R.layout.list_item_comment, parent, false))
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        val item = getItemForPosition(position)
        holder.body.text = item.body
    }

    private fun getItemForPosition(position: Int) : Comment = items[position]

    fun add(comments: List<Comment>) {
        items.clear()
        items.addAll(comments)
        notifyDataSetChanged()
    }

}