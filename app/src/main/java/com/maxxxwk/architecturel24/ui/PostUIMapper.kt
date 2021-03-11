package com.maxxxwk.architecturel24.ui

import android.view.View
import com.maxxxwk.architecturel24.R
import com.maxxxwk.architecturel24.data.repository.AndroidResourcesRepository
import com.maxxxwk.architecturel24.domain.model.PostModel
import com.maxxxwk.architecturel24.domain.model.UserStatusTypes
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PostUIMapper @Inject constructor(
    private val androidResourcesRepository: AndroidResourcesRepository,
    private val dispatcher: CoroutineDispatcher
) {
    suspend fun map(postModel: PostModel) = withContext(dispatcher) {
        return@withContext when (postModel.userStatusTypes) {
            UserStatusTypes.NORMAL -> NormalUsersPostUIModel(
                postModel.userId,
                postModel.id,
                postModel.title,
                postModel.body,
                androidResourcesRepository.getColor(R.color.white),
                View.GONE
            )
            UserStatusTypes.WARNING -> NormalUsersPostUIModel(
                postModel.userId,
                postModel.id,
                postModel.title,
                postModel.body,
                androidResourcesRepository.getColor(R.color.user_with_warning_post_background),
                View.VISIBLE
            )
            UserStatusTypes.BANNED -> BannedUsersPostUIModel(
                postModel.userId,
                postModel.id
            )

        }
    }
}
