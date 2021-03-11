package com.maxxxwk.architecturel24.di

import com.maxxxwk.architecturel24.data.PostMapper
import com.maxxxwk.architecturel24.data.repository.AndroidResourcesRepository
import com.maxxxwk.architecturel24.domain.SortingPostsUseCase
import com.maxxxwk.architecturel24.ui.PostUIMapper
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module(includes = [RepositoryModule::class, UseCaseModule::class])
class MapperModule {
    @Provides
    @Singleton
    fun providePostUIMapper(androidResourcesRepository: AndroidResourcesRepository): PostUIMapper {
        return PostUIMapper(
            androidResourcesRepository,
            Dispatchers.Main
        )
    }

    @Provides
    @Singleton
    fun providePostMapper(sortingPostsUseCase: SortingPostsUseCase): PostMapper {
        return PostMapper(sortingPostsUseCase, Dispatchers.Default)
    }
}
