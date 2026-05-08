package com.neouul.umc10android.todo.core.di

import com.neouul.umc10android.todo.data.repository.TodoRepositoryImpl
import com.neouul.umc10android.todo.domain.repository.TodoRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindTodoRepository(impl: TodoRepositoryImpl): TodoRepository
}