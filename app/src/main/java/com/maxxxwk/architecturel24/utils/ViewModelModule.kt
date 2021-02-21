package com.maxxxwk.architecturel24.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.maxxxwk.architecturel24.presentation.PostCreationActivityViewModel
import com.maxxxwk.architecturel24.presentation.PostsActivityViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(PostsActivityViewModel::class)
    abstract fun bindMainActivityViewModel(viewModel: PostsActivityViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PostCreationActivityViewModel::class)
    abstract fun bindCreateActivityViewModel(viewModel: PostCreationActivityViewModel): ViewModel
}