package com.maxxxwk.architecturel24.ui.posts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.maxxxwk.architecturel24.R
import com.maxxxwk.architecturel24.databinding.BannedUsersPostItemBinding
import com.maxxxwk.architecturel24.databinding.NormalUsersPostItemBinding
import com.maxxxwk.architecturel24.ui.BannedUsersPostUIModel
import com.maxxxwk.architecturel24.ui.NormalUsersPostUIModel
import com.maxxxwk.architecturel24.ui.PostUIModel

class PostsListAdapter :
    ListAdapter<PostUIModel, PostsListAdapter.PostViewHolder>(PostUIModelDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return if (viewType == R.layout.banned_users_post_item) {
            BannedPostViewHolder(layout)
        } else {
            NormalUsersPostViewHolder(layout)
        }
    }


    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemViewType(position: Int) = when (getItem(position)) {
        is NormalUsersPostUIModel -> R.layout.normal_users_post_item
        is BannedUsersPostUIModel -> R.layout.banned_users_post_item
    }


    abstract class PostViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        abstract fun bind(postUIModel: PostUIModel)
    }

    class NormalUsersPostViewHolder(view: View) : PostViewHolder(view) {
        private val binding = DataBindingUtil.bind<NormalUsersPostItemBinding>(view)

        override fun bind(postUIModel: PostUIModel) {
            binding?.post = postUIModel as NormalUsersPostUIModel
        }
    }

    class BannedPostViewHolder(view: View) : PostViewHolder(view) {
        private val binding = DataBindingUtil.bind<BannedUsersPostItemBinding>(view)

        override fun bind(postUIModel: PostUIModel) {
            binding?.post = postUIModel as BannedUsersPostUIModel
        }
    }

}
