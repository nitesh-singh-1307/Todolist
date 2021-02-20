package com.demo.todolist.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.demo.todolist.roomdatabase.Todolistdatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class UpdateTodoListViewModel(application: Application ) : AndroidViewModel(application) {
    val todolistdatabase = Todolistdatabase.getInstance(application)
    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private val _updateValue = MutableLiveData<Int>()
    val updateValue : LiveData<Int>
    get() = _updateValue

    init {
        clearUpdate()
    }

    fun updateTodoListItem(
        todoid: Int,
        _str_name: String,
        _str_discrption: String,
        _str_date: String
    ) {
        coroutineScope.launch(Dispatchers.IO) {
            val value = todolistdatabase?.Todolistdao()?.updateTodolist(todoid, _str_name, _str_discrption, _str_date)
            Log.e("<<update>>>","<<update>>>---<<$value")
            _updateValue.postValue(value)
        }
    }

    fun clearUpdate(){
        _updateValue.value = -1
    }
}