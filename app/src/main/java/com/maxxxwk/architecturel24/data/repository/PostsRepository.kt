package com.maxxxwk.architecturel24.data.repository

import com.maxxxwk.architecturel24.data.JSONPlaceholderService
import com.maxxxwk.architecturel24.data.database.PostDatabase
import com.maxxxwk.architecturel24.data.PostEntity
import com.maxxxwk.architecturel24.data.PostMapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PostsRepository @Inject constructor(
    private val jsonPlaceholderService: JSONPlaceholderService,
    private val postMapper: PostMapper,
    private val userStatusRepository: UserStatusRepository,
    private val db: PostDatabase,
    private val dispatcher: CoroutineDispatcher
) {

    suspend fun getPosts() = withContext(dispatcher) {
        val bannedUsersIds = userStatusRepository.getBannedUsersIdList()
        val usersWithWarningsIds = userStatusRepository.getUsersWithWarningsIdList()
        val postsFromLocalDatabase = db.postDao().getAll()
        if (postsFromLocalDatabase.isNotEmpty()) {
            return@withContext postMapper.map(
                postsFromLocalDatabase,
                bannedUsersIds,
                usersWithWarningsIds
            )
        } else {
            val postsFromRemoteStorage = jsonPlaceholderService.postsList().map {
                PostEntity(it.id, it.title, it.body, it.userId, true)
            }
            db.postDao().insertAll(*postsFromRemoteStorage.toTypedArray())
            return@withContext postMapper.map(
                postsFromRemoteStorage,
                bannedUsersIds,
                usersWithWarningsIds
            )
        }
    }

    suspend fun createNewPost(post: PostEntity) = withContext(dispatcher) {
        db.postDao().insertAll(post)
    }
}