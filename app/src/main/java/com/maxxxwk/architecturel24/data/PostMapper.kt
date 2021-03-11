package com.maxxxwk.architecturel24.data

import com.maxxxwk.architecturel24.domain.SortingPostsUseCase
import com.maxxxwk.architecturel24.domain.model.PostModel
import com.maxxxwk.architecturel24.domain.model.UserStatusTypes
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PostMapper @Inject constructor(
    private val sortingPostsUseCase: SortingPostsUseCase,
    private val dispatcher: CoroutineDispatcher
) {

    suspend fun map(
        posts: List<PostEntity>,
        bannedUsersIds: List<Int>,
        usersWithWarningIds: List<Int>
    ) = withContext(dispatcher) {
        sortingPostsUseCase(posts).map {
            val userStatus = when (it.userId) {
                in usersWithWarningIds -> UserStatusTypes.WARNING
                in bannedUsersIds -> UserStatusTypes.BANNED
                else -> UserStatusTypes.NORMAL
            }
            PostModel(it.userId, it.id, it.title, it.body, userStatus)
        }
    }
}
