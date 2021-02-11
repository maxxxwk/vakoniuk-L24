package com.maxxxwk.architecturel24.presentation.mapper

import android.view.View
import com.maxxxwk.architecturel24.R
import com.maxxxwk.architecturel24.domain.model.PostModel
import com.maxxxwk.architecturel24.domain.model.UserStatusTypes
import com.maxxxwk.architecturel24.presentation.model.PostUIModel

class PostUIMapper {
    fun map(postModels: List<PostModel>): List<PostUIModel> {
        return postModels.map {
            val (isBanned, warningVisibility, backgroundColor) = when (it.userStatusTypes) {
                UserStatusTypes.NORMAL -> Triple(false, View.GONE, R.color.white)
                UserStatusTypes.WARNING -> Triple(
                    false,
                    View.VISIBLE,
                    R.color.user_with_warning_post_background
                )
                UserStatusTypes.BANNED -> Triple(
                    true,
                    View.GONE,
                    R.color.banned_user_item_background
                )

            }
            PostUIModel(
                it.id,
                it.userId,
                it.title,
                it.body,
                backgroundColor,
                isBanned,
                warningVisibility
            )
        }
    }
}