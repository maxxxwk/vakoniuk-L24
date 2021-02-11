package com.maxxxwk.architecturel24.utils

import android.content.Context
import com.maxxxwk.architecturel24.data.api.JSONPlaceholderService
import com.maxxxwk.architecturel24.data.mapper.PostsMapper
import com.maxxxwk.architecturel24.data.repository.PostsRepositoryImpl
import com.maxxxwk.architecturel24.data.repository.UserStatusRepositoryImpl
import com.maxxxwk.architecturel24.presentation.PostsPresenter
import com.maxxxwk.architecturel24.presentation.mapper.PostUIMapper
import com.maxxxwk.architecturel24.utils.multithreading.Multithreading
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PostsComponent {

    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

    fun createPresenter(context: Context): PostsPresenter {
        return PostsPresenter(
            PostsRepositoryImpl(
                multithreading = Multithreading(context),
                jsonPlaceholderService = createPostsService(),
                postsMapper = PostsMapper(
                    userStatusRepository = UserStatusRepositoryImpl()
                )
            ),
            PostUIMapper()
        )
    }

    private fun createPostsService():JSONPlaceholderService{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(JSONPlaceholderService::class.java)
    }
}