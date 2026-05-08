package com.neouul.umc10android.todo.core.di

import android.content.Context
import androidx.room.Room
import com.neouul.umc10android.todo.data.local.TodoDao
import com.neouul.umc10android.todo.data.local.TodoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)  // 앱 전체에서 하나만 유지
object DatabaseModule {

    @Provides
    @Singleton  // ← DB는 무조건 하나만! 없으면 호출마다 새로 생성됨
    fun provideTodoDatabase(@ApplicationContext context: Context): TodoDatabase {
        return Room.databaseBuilder(context, TodoDatabase::class.java, "todo_db").build()
    }

    @Provides
    fun provideTodoDao(database: TodoDatabase): TodoDao = database.todoDao()
}