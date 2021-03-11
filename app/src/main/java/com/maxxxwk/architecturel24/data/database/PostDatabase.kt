package com.maxxxwk.architecturel24.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.maxxxwk.architecturel24.data.PostEntity

@Database(entities = [PostEntity::class], version = 1)
abstract class PostDatabase : RoomDatabase() {
    abstract fun postDao(): PostDao
}
