package com.maxxxwk.architecturel24.utils

import com.maxxxwk.architecturel24.presentation.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ViewModelModule::class])
interface AppComponent {
    fun inject(activity: MainActivity)
}