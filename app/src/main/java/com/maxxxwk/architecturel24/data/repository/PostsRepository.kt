package com.maxxxwk.architecturel24.data.repository

import com.maxxxwk.architecturel24.domain.model.PostModel
import com.maxxxwk.architecturel24.utils.multithreading.AsyncOperation

interface PostsRepository {
    fun getPosts(): AsyncOperation<List<PostModel>>
}