package com.maxxxwk.architecturel24.domain.postValidation

import com.maxxxwk.architecturel24.R
import com.maxxxwk.architecturel24.data.PostEntity
import com.maxxxwk.architecturel24.data.repository.AndroidResourcesRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.util.Locale
import javax.inject.Inject

class PostValidationUseCase @Inject constructor(
    private val androidResourcesRepository: AndroidResourcesRepository,
    private val dispatcher: CoroutineDispatcher
) {
    companion object {
        private const val MIN_TITLE_LENGTH = 3
        private const val MIN_BODY_LENGTH = 5
    }

    suspend operator fun invoke(postEntity: PostEntity) = withContext(dispatcher) {
        when {
            postEntity.title.replace(" ", "").length < MIN_TITLE_LENGTH -> {
                PostValidationResult.TOO_SMALL_TITLE
            }
            postEntity.body.replace(" ", "").length < MIN_BODY_LENGTH -> {
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
}
