package com.maxxxwk.architecturel24.di

import android.content.Context
import androidx.room.Room
import com.maxxxwk.architecturel24.data.database.PostDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ContextModule::class])
class DatabaseModule {
    companion object {
        private const val DATABASE_NAME = "PostsDatabase"
    }

    @Provides
    @Singleton
    fun provideDatabase(context: Context): PostDatabase {
        return Room.databaseBuilder(context, PostDatabase::class.java, DATABASE_NAME).build()
    }
}
