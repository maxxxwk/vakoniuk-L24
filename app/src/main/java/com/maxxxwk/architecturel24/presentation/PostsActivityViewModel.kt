package com.maxxxwk.architecturel24.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.maxxxwk.architecturel24.data.repository.PostsRepository
import javax.inject.Inject

class PostsActivityViewModel @Inject constructor(
    private val postsRepository: PostsRepository,
    private val postUIMapper: PostUIMapper
) : ViewModel() {

    private val _postsLiveData = MutableLiveData<List<PostUIModel>>()
    val postsLiveData: LiveData<List<PostUIModel>> = _postsLiveData


    fun loadPosts() {
        postsRepository.getPosts().map(postUIMapper::map).postOnMainThread {
            _postsLiveData.value = it
        }
    }
}