package com.maxxxwk.architecturel24.presentation

import android.content.Context
import android.view.View
import com.maxxxwk.architecturel24.R
import com.maxxxwk.architecturel24.data.repository.AndroidResourcesRepository
import com.maxxxwk.architecturel24.domain.model.PostModel
import com.maxxxwk.architecturel24.domain.model.UserStatusTypes
import javax.inject.Inject

class PostUIMapper @Inject constructor(private val androidResourcesRepository: AndroidResourcesRepository) {
    fun map(postModels: List<PostModel>) = postModels.map {
        when (it.userStatusTypes) {
            UserStatusTypes.NORMAL -> NormalUsersPostUIModel(
                it.userId,
                it.id,
                it.title,
                it.body,
                androidResourcesRepository.getColor(R.color.white),
                View.GONE
            )
            UserStatusTypes.WARNING -> NormalUsersPostUIModel(
                it.userId,
                it.id,
                it.title,
                it.body,
                androidResourcesRepository.getColor(R.color.user_with_warning_post_background),
                View.VISIBLE
            )
            UserStatusTypes.BANNED -> BannedUsersPostUIModel(
                it.userId,
                it.id
            )
        }
    }
}