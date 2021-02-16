package com.maxxxwk.architecturel24.presentation.mapper

import android.content.Context
import android.view.View
import com.maxxxwk.architecturel24.R
import com.maxxxwk.architecturel24.domain.model.PostModel
import com.maxxxwk.architecturel24.domain.model.UserStatusTypes
import com.maxxxwk.architecturel24.presentation.model.PostUIModel

class PostUIMapper(private val context: Context) {
    fun map(postModels: List<PostModel>): List<PostUIModel> {

        return postModels.map {
            val (isBanned, warningVisibility, backgroundColor) = when (it.userStatusTypes) {
                UserStatusTypes.NORMAL -> Triple(false, View.GONE,  context.getColor(R.color.white))
                UserStatusTypes.WARNING -> Triple(
                    false,
                    View.VISIBLE,
                    context.getColor(R.color.user_with_warning_post_background)
                )
                UserStatusTypes.BANNED -> Triple(
                    true,
                    View.GONE,
                    context.getColor(R.color.banned_user_item_background)
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