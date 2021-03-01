package com.maxxxwk.architecturel24.data

import com.maxxxwk.architecturel24.data.database.PostEntity
import retrofit2.Call
import retrofit2.http.GET

interface JSONPlaceholderService {
    @GET("/posts")
    fun postsList(): Call<List<PostEntity>>
}