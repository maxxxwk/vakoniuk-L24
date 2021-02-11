package com.maxxxwk.architecturel24.data.api

import com.maxxxwk.architecturel24.data.model.Post
import retrofit2.Call
import retrofit2.http.GET

interface JSONPlaceholderService {
    @GET("/posts")
    fun postsList(): Call<List<Post>>
}