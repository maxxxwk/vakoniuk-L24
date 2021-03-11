package com.maxxxwk.architecturel24.ui

import androidx.annotation.ColorInt

sealed class PostUIModel(
    open val userId: Int,
    open val id: Int
)

data class NormalUsersPostUIModel(
    override val userId: Int,
    override val id: Int,
    val title: String,
    val body: String,
    @ColorInt val backgroundColor: Int,
    val warningVisibility: Int
) : PostUIModel(userId, id)

data class BannedUsersPostUIModel(
    override val userId: Int,
    override val id: Int
) : PostUIModel(userId, id)
