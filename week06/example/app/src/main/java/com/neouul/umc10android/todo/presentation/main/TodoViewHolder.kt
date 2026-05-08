package com.neouul.umc10android.todo.presentation.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.neouul.umc10android.todo.databinding.ItemTodoListBinding
import com.neouul.umc10android.todo.domain.model.Todo

class TodoViewHolder(
    private val binding: ItemTodoListBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        todo: Todo,
        onTodoCheckedChange: (Todo) -> Unit,
        onTodoDelete: (Todo) -> Unit
    ) {
        binding.todo = todo
        binding.cbCompleted.setOnCheckedChangeListener(null) // 기존 리스너 제거
        binding.cbCompleted.isChecked = todo.isCompleted
        binding.cbCompleted.setOnCheckedChangeListener { _, _ ->
            onTodoCheckedChange(todo)
        }
        
        // 길게 누르면 삭제하도록 구현 (예시)
        binding.root.setOnLongClickListener {
            onTodoDelete(todo)
            true
        }
        
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): TodoViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemTodoListBinding.inflate(layoutInflater, parent, false)
            return TodoViewHolder(binding)
        }
    }
}