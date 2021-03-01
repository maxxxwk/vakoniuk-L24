package com.maxxxwk.architecturel24.domain

import com.maxxxwk.architecturel24.data.database.PostEntity
import javax.inject.Inject

class SortingPostsUseCase @Inject constructor() {
    operator fun invoke(posts: List<PostEntity>): List<PostEntity> {
        return posts.filter { !it.isFromRemoteStorage }
            .sortedByDescending { it.id } + posts.filter { it.isFromRemoteStorage }
    }
}