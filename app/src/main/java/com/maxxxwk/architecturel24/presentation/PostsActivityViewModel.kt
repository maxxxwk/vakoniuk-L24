package com.maxxxwk.architecturel24.presentation

import androidx.lifecycle.ViewModel
import com.maxxxwk.architecturel24.data.repository.PostsRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class PostsActivityViewModel @Inject constructor(
    private val postsRepository: PostsRepository,
    private val postUIMapper: PostUIMapper
) : ViewModel() {


    fun loadPosts(): Single<List<PostUIModel>> {
        return postsRepository.getPosts().map(postUIMapper::map)
            .observeOn((AndroidSchedulers.mainThread()))
    }
}