package com.neouul.umc10android.todo.data.mapper

import com.neouul.umc10android.todo.data.local.TodoEntity
import com.neouul.umc10android.todo.domain.model.Todo

fun TodoEntity.toDomain(): Todo {
    return Todo(
        id = id,
        title = title,
        isCompleted = isCompleted,
        createdAt = createdAt
    )
}

fun Todo.toEntity(): TodoEntity {
    return TodoEntity(
        id = id,
        title = title,
        isCompleted = isCompleted,
        createdAt = createdAt
    )
}