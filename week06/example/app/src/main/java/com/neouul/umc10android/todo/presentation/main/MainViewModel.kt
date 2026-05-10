package com.neouul.umc10android.todo.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neouul.umc10android.todo.domain.model.Todo
import com.neouul.umc10android.todo.domain.repository.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: TodoRepository,  // ← Interface 타입! 구현체가 뭔지 몰라도 됨
) : ViewModel() {

    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    init {
        // 앱 시작 시 DB 변화를 구독
        viewModelScope.launch {
            repository.getTodosStream().collect { todos ->
                _uiState.update { it.copy(todos = todos) }
            }
        }
    }

    fun addTodo(title: String) {
        if (title.isBlank()) return
        viewModelScope.launch {
            repository.insert(
                Todo(title = title)
            )
        }
    }

    fun toggleComplete(todo: Todo) {
        viewModelScope.launch {
            repository.update(
                todo.copy(isCompleted = !todo.isCompleted)
            )
        }
    }

    fun deleteTodo(todo: Todo) {
        viewModelScope.launch {
            repository.delete(todo)
        }
    }
}