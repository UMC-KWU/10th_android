package com.neouul.umc10android.todo.presentation.main

import com.neouul.umc10android.todo.domain.model.Todo

// UI 상태를 하나의 데이터 클래스로 관리
data class MainUiState(
    val todos: List<Todo> = emptyList()
)