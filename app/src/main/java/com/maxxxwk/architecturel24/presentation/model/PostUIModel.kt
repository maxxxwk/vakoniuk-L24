package com.maxxxwk.architecturel24.presentation.model

import androidx.annotation.ColorInt
import androidx.annotation.ColorRes

data class PostUIModel(
    val id: Int,
    val userId: Int,
    val title: String,
    val body: String,
    @ColorInt val backgroundColor: Int,
    val isBanned: Boolean,
    val warningVisibility: Int
)