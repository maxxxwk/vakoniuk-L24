package com.maxxxwk.architecturel24.domain

import com.maxxxwk.architecturel24.data.PostEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SortingPostsUseCase @Inject constructor(
    private val dispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(posts: List<PostEntity>) = withContext(dispatcher) {
        posts.filter { !it.isFromRemoteStorage }
            .sortedByDescending { it.id } + posts.filter { it.isFromRemoteStorage }
    }
}
