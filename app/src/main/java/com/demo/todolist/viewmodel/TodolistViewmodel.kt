package com.demo.todolist.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.demo.todolist.modules.Todolist
import com.demo.todolist.roomdatabase.Todolistdatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class TodolistViewmodel(application: Application) : AndroidViewModel(application) {
    var todolist: List<Todolist>? = null

    val todolistdatabase = Todolistdatabase.getInstance(application)
    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    fun todolistInsert(
        todo_work: String,
        todo_discription: String,
        todo_date: String,
        todo_priority: String
    ) {
        val _todolist_model = Todolist(0, todo_work, todo_discription, todo_date, todo_priority , 1)
        coroutineScope.launch(Dispatchers.IO) {
            todolistdatabase?.Todolistdao()?.insertTodolist(_todolist_model)
        }

    }


    fun getallTodolist(): LiveData<List<Todolist>> {
        return todolistdatabase?.Todolistdao()?.getTodolist()!!
    }

    fun deleteTodolist(_todoid: Int) {
        coroutineScope.launch(Dispatchers.IO) {
            todolistdatabase?.Todolistdao()?.deleteByUserId(_todoid)
        }
    }


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}