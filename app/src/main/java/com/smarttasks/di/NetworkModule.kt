package com.smarttasks.di

import com.smarttasks.network.RetrofitBuilder
import com.smarttasks.api.TaskListApi
import com.smarttasks.repository.TaskListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return RetrofitBuilder.retrofit
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): TaskListApi {
        return retrofit.create(TaskListApi::class.java)
    }

    @Singleton
    @Provides
    fun provideRepository(taskListApi: TaskListApi): TaskListRepository {
        return TaskListRepository(taskListApi)
    }

}