package com.neouul.umc10android.todo.presentation.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.neouul.umc10android.todo.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private val todoAdapter: TodoAdapter by lazy {
        TodoAdapter(
            onTodoCheckedChange = { viewModel.toggleComplete(it) },
            onTodoDelete = { viewModel.deleteTodo(it) }
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // 데이터 바인딩 초기화
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 바인딩에 lifecycleOwner와 viewModel 연결
        binding.lifecycleOwner = this
        binding.vm = viewModel

        // 어댑터 설정
        binding.rvTodos.adapter = todoAdapter

        // UI 상태 관찰 및 어댑터 갱신
        lifecycleScope.launchWhenStarted {
            viewModel.uiState.collect { uiState ->
                todoAdapter.submitList(uiState.todos)
            }
        }
    }
}