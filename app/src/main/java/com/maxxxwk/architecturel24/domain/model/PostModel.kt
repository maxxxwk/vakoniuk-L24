package com.maxxxwk.architecturel24.domain.model

data class PostModel(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String,
    val userStatusTypes: UserStatusTypes
)