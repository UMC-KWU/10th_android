package com.neouul.umc10android.todo.domain.repository

import com.neouul.umc10android.todo.domain.model.Todo
import kotlinx.coroutines.flow.Flow

interface TodoRepository {
    fun getTodosStream(): Flow<List<Todo>>
    suspend fun insert(todo: Todo)
    suspend fun update(todo: Todo)
    suspend fun delete(todo: Todo)
}