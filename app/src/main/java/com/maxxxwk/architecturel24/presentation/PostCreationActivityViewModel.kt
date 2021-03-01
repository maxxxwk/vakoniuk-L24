package com.maxxxwk.architecturel24.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import com.maxxxwk.architecturel24.data.database.PostEntity
import com.maxxxwk.architecturel24.data.repository.PostsRepository
import com.maxxxwk.architecturel24.domain.postValidation.PostValidationResult
import com.maxxxwk.architecturel24.domain.postValidation.PostValidationUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class PostCreationActivityViewModel @Inject constructor(
    private val postsRepository: PostsRepository,
    private val postValidationUseCase: PostValidationUseCase
) : ViewModel() {

    companion object {
        private const val USER_ID = 11
    }

    fun createPost(
        title: String,
        body: String,
        onValidationErrorCallback: (PostValidationResult) -> Unit,
        onSuccessfulCallback: () -> Unit
    ) {
        val post = PostEntity(
            title = title,
            body = body,
            userId = USER_ID,
            isFromRemoteStorage = false
        )
        val postValidationResult = postValidationUseCase(post)
        if (postValidationResult == PostValidationResult.SUCCESSFUL) {
            postsRepository.createNewPost(post).observeOn(Schedulers.newThread())
                .subscribe { onSuccessfulCallback() }
        } else {
            onValidationErrorCallback(postValidationResult)
        }
    }
}