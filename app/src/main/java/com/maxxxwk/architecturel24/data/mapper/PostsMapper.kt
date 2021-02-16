package com.maxxxwk.architecturel24.data.mapper

import com.maxxxwk.architecturel24.data.model.Post
import com.maxxxwk.architecturel24.data.repository.UserStatusRepository
import com.maxxxwk.architecturel24.domain.model.PostModel
import com.maxxxwk.architecturel24.domain.model.UserStatusTypes
import javax.inject.Inject

class PostsMapper @Inject constructor(
    private val userStatusRepository: UserStatusRepository
) {

    fun map(posts: List<Post>): List<PostModel> {
        return posts.map {
            val userStatus = when (it.userId) {
                in userStatusRepository.getUsersWithWarningsIdList() -> UserStatusTypes.WARNING
                in userStatusRepository.getBannedUsersIdList() -> UserStatusTypes.BANNED
                else -> UserStatusTypes.NORMAL
            }
            PostModel(it.userId ,it.id, it.title, it.body, userStatus)
        }
    }
}