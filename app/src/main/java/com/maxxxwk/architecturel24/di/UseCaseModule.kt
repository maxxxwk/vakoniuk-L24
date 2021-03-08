package com.maxxxwk.architecturel24.di

import com.maxxxwk.architecturel24.data.repository.AndroidResourcesRepository
import com.maxxxwk.architecturel24.data.repository.PostsRepository
import com.maxxxwk.architecturel24.domain.PostCreationUseCase
import com.maxxxwk.architecturel24.domain.SortingPostsUseCase
import com.maxxxwk.architecturel24.domain.postValidation.PostValidationUseCase
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module(includes = [RepositoryModule::class])
class UseCaseModule {
    @Provides
    @Singleton
    fun providePostValidationUseCase(androidResourcesRepository: AndroidResourcesRepository): PostValidationUseCase {
        return PostValidationUseCase(androidResourcesRepository, Dispatchers.Default)
    }

    @Provides
    @Singleton
    fun provideSortingPostUseCase(): SortingPostsUseCase {
        return SortingPostsUseCase(Dispatchers.Default)
    }

    @Provides
    @Singleton
    fun provideCreatePostUseCase(
        postsRepository: PostsRepository,
        postValidationUseCase: PostValidationUseCase
    ): PostCreationUseCase {
        return PostCreationUseCase(postsRepository, postValidationUseCase, Dispatchers.IO)
    }
}