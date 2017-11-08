package com.bakkenbaeck.mvvm.ui.comments

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.list_item_comment.view.*


class CommentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val body = itemView.body
}