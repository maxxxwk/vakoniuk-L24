package com.maxxxwk.architecturel24.domain

import com.maxxxwk.architecturel24.data.PostEntity
import com.maxxxwk.architecturel24.data.repository.PostsRepository
import com.maxxxwk.architecturel24.domain.postValidation.PostValidationResult
import com.maxxxwk.architecturel24.domain.postValidation.PostValidationUseCase
import com.maxxxwk.architecturel24.ui.postCreation.PostCreationResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PostCreationUseCase @Inject constructor(
    private val postsRepository: PostsRepository,
    private val postValidationUseCase: PostValidationUseCase,
    private val dispatcher: CoroutineDispatcher
) {
    companion object {
        private const val USER_ID = 11
    }

    suspend operator fun invoke(
        title: String,
        body: String
    ) = withContext(dispatcher) {
        val post = PostEntity(
            title = title,
            body = body,
            userId = USER_ID,
            isFromRemoteStorage = false
        )
        when (val postValidationResult = postValidationUseCase(post)) {
            PostValidationResult.SUCCESSFUL -> {
                postsRepository.createNewPost(post)
                return@withContext PostCreationResult.Successful
            }
            else -> {
                return@withContext PostCreationResult.Failure(postValidationResult)
            }
        }
    }
}
