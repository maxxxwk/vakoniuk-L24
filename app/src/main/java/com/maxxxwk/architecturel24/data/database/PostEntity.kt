package com.maxxxwk.architecturel24.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "posts")
data class PostEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "title")
    var title: String,
    @ColumnInfo(name = "body")
    var body: String,
    @ColumnInfo(name = "user_id")
    val userId: Int,
    @ColumnInfo(name = "is_from_remote_storage")
    val isFromRemoteStorage: Boolean
)
