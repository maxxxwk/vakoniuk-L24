package com.maxxxwk.architecturel24.data

import com.maxxxwk.architecturel24.data.repository.UserStatusRepository
import com.maxxxwk.architecturel24.domain.SortingPostsUseCase
import com.maxxxwk.architecturel24.domain.model.PostModel
import com.maxxxwk.architecturel24.domain.model.UserStatusTypes
import javax.inject.Inject

class PostMapper @Inject constructor(
    private val userStatusRepository: UserStatusRepository,
    private val sortingPostsUseCase: SortingPostsUseCase
) {

    fun map(posts: List<Post>): List<PostModel> {
        val usersWithWarnings = userStatusRepository.getUsersWithWarningsIdList()
        val bannedUsers = userStatusRepository.getBannedUsersIdList()
        return sortingPostsUseCase(posts).map {
            val userStatus = when (it.userId) {
                in usersWithWarnings -> UserStatusTypes.WARNING
                in bannedUsers -> UserStatusTypes.BANNED
                else -> UserStatusTypes.NORMAL
            }
            PostModel(it.userId, it.id, it.title, it.body, userStatus)
        }
    }
}