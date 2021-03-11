package com.maxxxwk.architecturel24.ui.posts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maxxxwk.architecturel24.data.repository.PostsRepository
import com.maxxxwk.architecturel24.ui.PostUIMapper
import com.maxxxwk.architecturel24.ui.PostUIModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class PostsActivityViewModel @Inject constructor(
    private val postsRepository: PostsRepository,
    private val postUIMapper: PostUIMapper
) : ViewModel() {

    private val _postsLiveData = MutableLiveData<List<PostUIModel>>()
    val postsLiveData: LiveData<List<PostUIModel>> = _postsLiveData


    fun loadPosts() {
        viewModelScope.launch {
            _postsLiveData.value = postsRepository.getPosts().map {
                postUIMapper.map(it)
            }
        }
    }
}
