package com.maxxxwk.architecturel24.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Post(
    @SerializedName("userId")
    val userId: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("body")
    val body: String,
    @Expose(serialize = false, deserialize = false)
    val isFromRemoteStorage: Boolean = true
)