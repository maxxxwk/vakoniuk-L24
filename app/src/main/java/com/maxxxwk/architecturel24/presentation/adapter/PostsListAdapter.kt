package com.maxxxwk.architecturel24.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.maxxxwk.architecturel24.R
import com.maxxxwk.architecturel24.databinding.BannedUserPostItemBinding
import com.maxxxwk.architecturel24.databinding.UserPostItemBinding
import com.maxxxwk.architecturel24.presentation.callback.PostUIModelDiffCallback
import com.maxxxwk.architecturel24.presentation.model.PostUIModel

class PostsListAdapter :
    ListAdapter<PostUIModel, PostsListAdapter.PostViewHolder>(PostUIModelDiffCallback()) {

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
        private val binding = UserPostItemBinding.bind(view)

        override fun bind(postUIModel: PostUIModel) {
            with(binding) {
                root.setBackgroundResource(postUIModel.backgroundColorRes)
                binding.tvWarning.visibility = postUIModel.warningVisibility
                tvUserId.text =
                    root.context.getString(R.string.user_post_item_user_id_text, postUIModel.userId)
                tvTitle.text = postUIModel.title
                tvBody.text = postUIModel.body
            }
        }
    }

    class BannedPostViewHolder(view: View) : PostViewHolder(view) {
        private val binding = BannedUserPostItemBinding.bind(view)

        override fun bind(postUIModel: PostUIModel) {
            with(binding) {
                root.setBackgroundResource(postUIModel.backgroundColorRes)
                tvMessage.text = binding.root.context.getString(
                    R.string.banned_user_post_item_message_text,
                    postUIModel.userId
                )
            }
        }
    }

}