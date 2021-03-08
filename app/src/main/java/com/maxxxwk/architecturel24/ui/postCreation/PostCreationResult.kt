package com.maxxxwk.architecturel24.ui.postCreation

import com.maxxxwk.architecturel24.domain.postValidation.PostValidationResult

sealed class PostCreationResult {
    object Successful : PostCreationResult()
    data class Failure(val postValidationResult: PostValidationResult) : PostCreationResult()
}
