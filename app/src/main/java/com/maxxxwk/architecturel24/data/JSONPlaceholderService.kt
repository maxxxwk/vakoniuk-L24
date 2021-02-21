package com.maxxxwk.architecturel24.data

import retrofit2.Call
import retrofit2.http.GET

interface JSONPlaceholderService {
    @GET("/posts")
    fun postsList(): Call<List<Post>>
}