package com.neouul.umc10android.todo.data.repository

import com.neouul.umc10android.todo.data.local.TodoDao
import com.neouul.umc10android.todo.data.mapper.toDomain
import com.neouul.umc10android.todo.data.mapper.toEntity
import com.neouul.umc10android.todo.domain.model.Todo
import com.neouul.umc10android.todo.domain.repository.TodoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TodoRepositoryImpl @Inject constructor(
    private val todoDao: TodoDao,
) : TodoRepository {
    override fun getTodosStream(): Flow<List<Todo>> = 
        todoDao.getAllTodosFlow().map { list ->
            list.map { it.toDomain() }
        }

    override suspend fun insert(todo: Todo) = todoDao.insertTodo(todo.toEntity())
    override suspend fun update(todo: Todo) = todoDao.updateTodo(todo.toEntity())
    override suspend fun delete(todo: Todo) = todoDao.deleteTodo(todo.toEntity())
}