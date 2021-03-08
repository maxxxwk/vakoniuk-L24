package com.maxxxwk.architecturel24.di

import android.content.Context
import com.maxxxwk.architecturel24.data.JSONPlaceholderService
import com.maxxxwk.architecturel24.data.PostMapper
import com.maxxxwk.architecturel24.data.database.PostDatabase
import com.maxxxwk.architecturel24.data.repository.AndroidResourcesRepository
import com.maxxxwk.architecturel24.data.repository.PostsRepository
import com.maxxxwk.architecturel24.data.repository.UserStatusRepository
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module(
    includes = [ContextModule::class, NetworkModule::class, DatabaseModule::class,
        MapperModule::class]
)
class RepositoryModule {
    @Provides
    @Singleton
    fun provideAndroidResourceRepository(
        context: Context
    ): AndroidResourcesRepository {
        return AndroidResourcesRepository(context, Dispatchers.Main)
    }

    @Provides
    @Singleton
    fun provideUserStatusRepository(): UserStatusRepository {
        return UserStatusRepository(Dispatchers.IO)
    }

    @Provides
    @Singleton
    fun providePostsRepository(
        jsonPlaceholderService: JSONPlaceholderService,
        postMapper: PostMapper,
        userStatusRepository: UserStatusRepository,
        db: PostDatabase
    ): PostsRepository {
        return PostsRepository(
            jsonPlaceholderService,
            postMapper,
            userStatusRepository,
            db,
            Dispatchers.IO
        )
    }
}