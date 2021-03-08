package com.maxxxwk.architecturel24.di

import com.maxxxwk.architecturel24.ui.postCreation.PostCreationActivity
import com.maxxxwk.architecturel24.ui.posts.PostsActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [ContextModule::class, ViewModelModule::class, NetworkModule::class,
        DatabaseModule::class, RepositoryModule::class, MapperModule::class, UseCaseModule::class]
)
interface AppComponent {
    fun inject(activity: PostsActivity)
    fun inject(activity: PostCreationActivity)
}