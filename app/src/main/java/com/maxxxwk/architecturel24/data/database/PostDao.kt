package com.maxxxwk.architecturel24.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PostDao {
    @Query("SELECT * FROM posts")
    fun getAll(): List<PostEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg posts: PostEntity)

    @Query("SELECT MAX(id) FROM posts")
    fun getMaxPostId(): Int
}