package com.test.android_todo_app

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class  TodoViewModal : ViewModel() {
    private val  _todos = MutableLiveData<List<Todo>>(emptyList())
    val todos: LiveData<List<Todo>> = _todos;


    fun addTodo (todo: Todo) {
        val currentList = _todos.value.orEmpty().toMutableList()
        currentList.add(todo)
        _todos.value = currentList
    }

   fun remove (id: Int) {
       val currentList = _todos.value.orEmpty().toMutableList()
        val newList =  currentList.filter { todo -> todo.id != id }
       _todos.value = newList
   }

    fun update (todo: Todo) {
        _todos.value = _todos.value.orEmpty().map {
            when (it.id) {
                todo.id -> todo
                else -> it
            }
        }
    }
}