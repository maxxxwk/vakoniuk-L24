package com.maxxxwk.architecturel24

import android.app.Application
import com.maxxxwk.architecturel24.di.AppComponent
import com.maxxxwk.architecturel24.di.ContextModule
import com.maxxxwk.architecturel24.di.DaggerAppComponent

class App : Application() {
    lateinit var daggerComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        daggerComponent = DaggerAppComponent.builder()
            .contextModule(ContextModule(this))
            .build()
    }
}
