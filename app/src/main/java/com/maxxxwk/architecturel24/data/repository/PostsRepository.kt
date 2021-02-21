package com.maxxxwk.architecturel24.data.repository

import com.maxxxwk.architecturel24.data.JSONPlaceholderService
import com.maxxxwk.architecturel24.data.database.PostDatabase
import com.maxxxwk.architecturel24.data.database.PostEntity
import com.maxxxwk.architecturel24.data.PostMapper
import com.maxxxwk.architecturel24.data.Post
import com.maxxxwk.architecturel24.domain.model.PostModel
import com.maxxxwk.architecturel24.utils.multithreading.AsyncOperation
import com.maxxxwk.architecturel24.utils.multithreading.Multithreading
import javax.inject.Inject

class PostsRepository @Inject constructor(
    private val multithreading: Multithreading,
    private val jsonPlaceholderService: JSONPlaceholderService,
    private val postMapper: PostMapper,
    private val db: PostDatabase
) {

    fun getPosts(): AsyncOperation<List<PostModel>> {
        return multithreading.async {
            val postsFromLocalDB = db.postDao().getAll()
            if (postsFromLocalDB.isNotEmpty()) {
                return@async postsFromLocalDB.map {
                    Post(
                        it.userId,
                        it.id,
                        it.title,
                        it.body,
                        it.isFromRemoteStorage
                    )
                }
            } else {
                val posts = jsonPlaceholderService.postsList().execute().body() ?: emptyList()
                db.postDao().insertAll(*posts.map {
                    PostEntity(
                        it.id,
                        it.title,
                        it.body,
                        it.userId,
                        it.isFromRemoteStorage
                    )
                }.toTypedArray())
                return@async posts
            }
        }.map(postMapper::map)
    }

    fun createNewPost(post: PostEntity): AsyncOperation<Unit> {
        return multithreading.async {
            db.postDao().insertAll(post)
        }
    }

    fun getMaxPostId(): AsyncOperation<Int> {
        return multithreading.async {
            db.postDao().getMaxPostId()
        }
    }
}