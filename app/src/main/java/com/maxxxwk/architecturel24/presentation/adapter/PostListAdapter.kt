package com.maxxxwk.architecturel24.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.maxxxwk.architecturel24.R
import com.maxxxwk.architecturel24.databinding.BannedUserPostItemBinding
import com.maxxxwk.architecturel24.databinding.UserPostItemBinding
import com.maxxxwk.architecturel24.presentation.callback.PostUIModelDiffCallback
import com.maxxxwk.architecturel24.presentation.model.PostUIModel

class PostListAdapter :
    ListAdapter<PostUIModel, PostListAdapter.PostViewHolder>(PostUIModelDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return when (viewType) {
            R.layout.banned_user_post_item -> BannedPostViewHolder(layout)
            else -> WithoutBanPostViewHolder(layout)
        }
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position).isBanned) {
            R.layout.banned_user_post_item
        } else {
            R.layout.user_post_item
        }
    }

    abstract class PostViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        abstract fun bind(postUIModel: PostUIModel)
    }

    class WithoutBanPostViewHolder(view: View) : PostViewHolder(view) {
        private val binding = DataBindingUtil.bind<UserPostItemBinding>(view)

        override fun bind(postUIModel: PostUIModel) {
            binding?.post = postUIModel
        }
    }

    class BannedPostViewHolder(view: View) : PostViewHolder(view) {
        private val binding = DataBindingUtil.bind<BannedUserPostItemBinding>(view)

        override fun bind(postUIModel: PostUIModel) {
            binding?.post = postUIModel
        }
    }

}