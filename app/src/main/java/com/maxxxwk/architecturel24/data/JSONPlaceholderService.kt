package com.maxxxwk.architecturel24.data

import retrofit2.http.GET

interface JSONPlaceholderService {
    @GET("/posts")
    suspend fun postsList(): List<PostEntity>
}