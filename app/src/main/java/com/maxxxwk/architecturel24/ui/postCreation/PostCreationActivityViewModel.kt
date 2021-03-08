package com.maxxxwk.architecturel24.ui.postCreation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maxxxwk.architecturel24.domain.PostCreationUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class PostCreationActivityViewModel @Inject constructor(
    private val postCreationUseCase: PostCreationUseCase
) : ViewModel() {

    private val _postCreationLiveData = MutableLiveData<PostCreationResult>()
    val postCreationLiveData: LiveData<PostCreationResult> = _postCreationLiveData

    fun createPost(
        title: String,
        body: String
    ) {
        viewModelScope.launch {
            _postCreationLiveData.value = postCreationUseCase(title, body)
        }
    }
}