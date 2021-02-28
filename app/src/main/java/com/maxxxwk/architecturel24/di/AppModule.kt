package com.maxxxwk.architecturel24.di

import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.maxxxwk.architecturel24.data.JSONPlaceholderService
import com.maxxxwk.architecturel24.data.database.PostDatabase
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule(private val context: Context) {

    companion object {
        private const val BASE_URL = "https://jsonplaceholder.typicode.com/"
        private const val DATABASE_NAME = "PostsDatabase"
    }

    @Provides
    @Singleton
    fun provideContext(): Context {
        return context
    }

    @Provides
    @Singleton
    fun provideJsonPlaceholderApi(retrofit: Retrofit): JSONPlaceholderService {
        return retrofit.create(JSONPlaceholderService::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofit(gsonConverterFactory: GsonConverterFactory): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Provides
    @Singleton
    fun provideGsonConverterFactory(gson: Gson): GsonConverterFactory {
        return GsonConverterFactory.create(gson)
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    @Provides
    @Singleton
    fun provideDatabase(context: Context): PostDatabase {
        return Room.databaseBuilder(context, PostDatabase::class.java, DATABASE_NAME).build()
    }
}