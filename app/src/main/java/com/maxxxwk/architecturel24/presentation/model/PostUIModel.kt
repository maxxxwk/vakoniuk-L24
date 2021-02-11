package com.maxxxwk.architecturel24.presentation.model

import androidx.annotation.ColorRes

data class PostUIModel(
    val id: Int,
    val userId: Int,
    val title: String,
    val body: String,
    @ColorRes val backgroundColorRes: Int,
    val isBanned: Boolean,
    val warningVisibility: Int
)