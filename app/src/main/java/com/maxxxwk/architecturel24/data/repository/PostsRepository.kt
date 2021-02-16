package com.maxxxwk.architecturel24.data.repository

import com.maxxxwk.architecturel24.data.api.JSONPlaceholderService
import com.maxxxwk.architecturel24.data.mapper.PostsMapper
import com.maxxxwk.architecturel24.domain.model.PostModel
import com.maxxxwk.architecturel24.utils.multithreading.AsyncOperation
import com.maxxxwk.architecturel24.utils.multithreading.Multithreading
import javax.inject.Inject

class PostsRepository @Inject constructor(
    private val multithreading: Multithreading,
    private val jsonPlaceholderService: JSONPlaceholderService,
    private val postsMapper: PostsMapper
) {

    fun getPosts(): AsyncOperation<List<PostModel>> {
        return multithreading.async {
            val posts = jsonPlaceholderService.postsList().execute().body()
            return@async posts ?: emptyList()
        }.map(postsMapper::map)
    }
}