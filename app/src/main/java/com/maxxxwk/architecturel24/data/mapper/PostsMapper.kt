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
        val usersWithWarnings = userStatusRepository.getUsersWithWarningsIdList()
        val bannedUsers = userStatusRepository.getBannedUsersIdList()
        val sortedPosts = sort(posts)
        return sortedPosts.map {
            val userStatus = when (it.userId) {
                in usersWithWarnings -> UserStatusTypes.WARNING
                in bannedUsers -> UserStatusTypes.BANNED
                else -> UserStatusTypes.NORMAL
            }
            PostModel(it.userId, it.id, it.title, it.body, userStatus)
        }
    }

    private fun sort(posts: List<Post>): List<Post> {
        return posts.filter { !it.isFromRemoteStorage }
            .sortedByDescending { it.id } + posts.filter { it.isFromRemoteStorage }
    }
}