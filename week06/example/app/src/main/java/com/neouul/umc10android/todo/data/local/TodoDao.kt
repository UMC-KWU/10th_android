package com.neouul.umc10android.todo.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface TodoDao {
    //새 상품을 삽입
    @Insert
    fun insertTodo(Todo: TodoEntity)

    //상품 정보를 수정
    @Update
    fun updateTodo(Todo: TodoEntity)

    //특정 상품을 삭제
    @Delete
    fun deleteTodo(Todo: TodoEntity)

    //모든 상품 데이터를 가져오는 쿼리
    @Query("SELECT * FROM todos")
    fun getAllTodos(): List<TodoEntity>

    //특정 카테고리의 상품만 가져오는 쿼리
//    @Query("SELECT * FROM todos WHERE ")
//    fun getTodosByCategory(categoryId: Int): List<TodoEntity>

}