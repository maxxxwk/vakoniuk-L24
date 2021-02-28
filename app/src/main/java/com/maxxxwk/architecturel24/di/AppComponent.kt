package com.maxxxwk.architecturel24.di

import com.maxxxwk.architecturel24.presentation.PostCreationActivity
import com.maxxxwk.architecturel24.presentation.PostsActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ViewModelModule::class])
interface AppComponent {
    fun inject(activity: PostsActivity)
    fun inject(activity: PostCreationActivity)
}