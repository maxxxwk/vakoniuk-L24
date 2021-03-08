package com.maxxxwk.architecturel24.ui.posts

import androidx.recyclerview.widget.DiffUtil
import com.maxxxwk.architecturel24.ui.PostUIModel

class PostUIModelDiffCallback : DiffUtil.ItemCallback<PostUIModel>() {
    override fun areItemsTheSame(oldItem: PostUIModel, newItem: PostUIModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: PostUIModel, newItem: PostUIModel): Boolean {
        return oldItem == newItem
    }
}