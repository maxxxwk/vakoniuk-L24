package com.maxxxwk.architecturel24.presentation

import com.maxxxwk.architecturel24.data.repository.PostsRepository
import com.maxxxwk.architecturel24.presentation.mapper.PostUIMapper
import com.maxxxwk.architecturel24.presentation.model.PostUIModel
import com.maxxxwk.architecturel24.utils.multithreading.CancelableOperation

class PostsPresenter(
    private val postsRepository: PostsRepository,
    private val postUIMapper: PostUIMapper
) {
    private var view: PostsView? = null
    private var cancelableOperation: CancelableOperation? = null

    fun attachView(postsView: PostsView) {
        view = postsView
        cancelableOperation =
            postsRepository.getPosts().map(postUIMapper::map).postOnMainThread(::showPosts)
    }

    fun detachView() {
        view = null
        cancelableOperation?.cancel()
    }

    private fun showPosts(posts: List<PostUIModel>) {
        view?.showPosts(posts)
    }
}