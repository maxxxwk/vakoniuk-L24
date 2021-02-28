package com.maxxxwk.architecturel24.domain.postValidation

import com.maxxxwk.architecturel24.data.database.PostEntity
import javax.inject.Inject

class PostValidationUseCase @Inject constructor() {
    operator fun invoke(postEntity: PostEntity) = when {
        postEntity.title.replace(" ", "").length < 3 -> PostValidationResult.TOO_SMALL_TITLE
        postEntity.body.replace(" ", "").length < 5 -> PostValidationResult.TOO_SMALL_BODY
        postEntity.title.toLowerCase().split(" ")
            .contains("реклама") -> PostValidationResult.INCORRECT_TITLE
        postEntity.title.toLowerCase().split(" ")
            .contains("товар") -> PostValidationResult.INCORRECT_TITLE
        postEntity.title.toLowerCase().split(" ")
            .contains("куплю") -> PostValidationResult.INCORRECT_TITLE
        else -> PostValidationResult.SUCCESSFUL
    }
}