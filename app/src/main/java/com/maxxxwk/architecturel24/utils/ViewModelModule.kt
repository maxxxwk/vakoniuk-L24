package com.maxxxwk.architecturel24.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.maxxxwk.architecturel24.presentation.CreatePostViewModel
import com.maxxxwk.architecturel24.presentation.MainActivityViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    abstract fun bindMainActivityViewModel(viewModel: MainActivityViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CreatePostViewModel::class)
    abstract fun bindCreateActivityViewModel(viewModel: CreatePostViewModel): ViewModel
}