package com.maxxxwk.architecturel24.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "posts")
data class PostEntity(
    @SerializedName("id")
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,
    @SerializedName("title")
    @ColumnInfo(name = "title")
    var title: String,
    @SerializedName("body")
    @ColumnInfo(name = "body")
    var body: String,
    @SerializedName("userId")
    @ColumnInfo(name = "user_id")
    val userId: Int,
    @Expose(serialize = false, deserialize = false)
    @ColumnInfo(name = "is_from_remote_storage")
    val isFromRemoteStorage: Boolean
)
