package com.maxxxwk.architecturel24.presentation

import com.maxxxwk.architecturel24.presentation.model.PostUIModel

interface PostsView {
    fun showPosts(posts: List<PostUIModel>)
}