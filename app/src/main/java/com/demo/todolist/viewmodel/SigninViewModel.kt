package com.demo.todolist.viewmodel

import android.app.Application
import android.content.Intent
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.demo.todolist.MainActivity
import com.demo.todolist.modules.Signup
import com.demo.todolist.modules.UserloginUp
import com.demo.todolist.roomdatabase.Todolistdatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class SigninViewModel(application: Application) : AndroidViewModel(application) {
    val todolistdatabase = Todolistdatabase.getInstance(application)
    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    var user_list: LiveData<List<UserloginUp>>? = null
    fun SignupDataInsert(
        view : View,
        user_name: String,
        user_email: String,
        user_mobile: String,
        user_type: String

    ) {
        val _userlist_model = Signup(0, user_name, user_mobile, user_email, user_type)
        val _userloginlist_model = UserloginUp(0, user_name, user_email)
        coroutineScope.launch(Dispatchers.IO) {
            todolistdatabase?.UsersDao()?.insertUserlist(_userlist_model)
            todolistdatabase?.UsersDao()?.inserttwoUserlist(_userloginlist_model)

            val intent = Intent(view.context, MainActivity::class.java)
            view.context.startActivity(intent);
        }
    }


    fun selectUser(): LiveData<List<UserloginUp>> {
        user_list = todolistdatabase?.UsersDao()?.getUsersLogin()
        return user_list!!
    }



}