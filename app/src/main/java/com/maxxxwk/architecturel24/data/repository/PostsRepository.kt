package com.maxxxwk.architecturel24.data.repository

import com.maxxxwk.architecturel24.data.JSONPlaceholderService
import com.maxxxwk.architecturel24.data.database.PostDatabase
import com.maxxxwk.architecturel24.data.database.PostEntity
import com.maxxxwk.architecturel24.data.PostMapper
import com.maxxxwk.architecturel24.data.Post
import com.maxxxwk.architecturel24.domain.model.PostModel
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class PostsRepository @Inject constructor(
    private val jsonPlaceholderService: JSONPlaceholderService,
    private val postMapper: PostMapper,
    private val userStatusRepository: UserStatusRepository,
    private val db: PostDatabase
) {

    fun getPosts(): Single<List<PostModel>> {
        val postsSingle: Single<List<Post>> = Single.create { emitter ->
            val postsFromLocalDatabase = db.postDao().getAll().map {
                Post(
                    it.userId,
                    it.id,
                    it.title,
                    it.body,
                    it.isFromRemoteStorage
                )
            }
            if (postsFromLocalDatabase.isNotEmpty()) {
                emitter.onSuccess(postsFromLocalDatabase)
            } else {
                val postsFromRemoteStorage =
                    jsonPlaceholderService.postsList().execute().body()?.map {
                        Post(it.userId, it.id, it.title, it.body, true)
                    } ?: emptyList()
                db.postDao().insertAll(*postsFromRemoteStorage.map {
                    PostEntity(
                        it.id,
                        it.title,
                        it.body,
                        it.userId,
                        it.isFromRemoteStorage
                    )
                }.toTypedArray())
                emitter.onSuccess(postsFromRemoteStorage)
            }
        }
        return Single.zip(
            postsSingle,
            userStatusRepository.getBannedUsersIdList(),
            userStatusRepository.getUsersWithWarningsIdList(),
            postMapper::map
        ).subscribeOn(Schedulers.io())
    }

    fun createNewPost(post: PostEntity): Completable {
        return Completable.create { source ->
            db.postDao().insertAll(post)
            source.onComplete()
        }.subscribeOn(Schedulers.io())
    }

    fun getMaxPostId(): Single<Int> {
        return Single.create<Int> { emitter ->
            emitter.onSuccess(db.postDao().getMaxPostId())
        }.subscribeOn(Schedulers.io())
    }
}