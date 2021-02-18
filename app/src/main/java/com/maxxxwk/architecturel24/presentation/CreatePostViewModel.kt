package com.maxxxwk.architecturel24.presentation

import androidx.lifecycle.ViewModel
import com.maxxxwk.architecturel24.data.database.PostEntity
import com.maxxxwk.architecturel24.data.repository.PostsRepository
import com.maxxxwk.architecturel24.domain.postValidation.PostValidationResult
import com.maxxxwk.architecturel24.domain.postValidation.PostValidationUseCase
import javax.inject.Inject

class CreatePostViewModel @Inject constructor(
    private val postsRepository: PostsRepository,
    private val postValidationUseCase: PostValidationUseCase
) : ViewModel() {

    companion object {
        private const val USER_ID = 11
    }

    fun createPost(
        title: String,
        body: String,
        onErrorCallback: (PostValidationResult) -> Unit,
        onSuccessfulCallback: () -> Unit
    ) {
        postsRepository.getMaxPostId().postOnMainThread { maxId ->
            val post = PostEntity(
                maxId + 1,
                title,
                body,
                USER_ID,
                false
            )
            val postValidationResult = postValidationUseCase(post)
            if (postValidationResult == PostValidationResult.SUCCESSFUL) {
                postsRepository.createNewPost(post).postOnMainThread {
                    onSuccessfulCallback()
                }
            } else {
                onErrorCallback(postValidationResult)
            }
        }
    }
}