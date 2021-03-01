package com.maxxxwk.architecturel24.domain.postValidation

import com.maxxxwk.architecturel24.R
import com.maxxxwk.architecturel24.data.database.PostEntity
import com.maxxxwk.architecturel24.data.repository.AndroidResourcesRepository
import java.util.*
import javax.inject.Inject

class PostValidationUseCase @Inject constructor(
    private val androidResourcesRepository: AndroidResourcesRepository
) {
    operator fun invoke(postEntity: PostEntity) = when {
        postEntity.title.replace(" ", "").length < 3 -> {
            PostValidationResult.TOO_SMALL_TITLE
        }
        postEntity.body.replace(" ", "").length < 5 -> {
            PostValidationResult.TOO_SMALL_BODY
        }
        postEntity.title.toLowerCase(Locale.ROOT).split(" ")
            .contains(androidResourcesRepository.getString(R.string.forbidden_word_advertisement)) -> {
            PostValidationResult.INCORRECT_TITLE
        }
        postEntity.title.toLowerCase(Locale.ROOT).split(" ")
            .contains(androidResourcesRepository.getString(R.string.forbidden_word_goods)) -> {
            PostValidationResult.INCORRECT_TITLE
        }
        postEntity.title.toLowerCase(Locale.ROOT).split(" ")
            .contains(androidResourcesRepository.getString(R.string.forbidden_word_buy)) -> {
            PostValidationResult.INCORRECT_TITLE
        }
        else -> PostValidationResult.SUCCESSFUL
    }
}