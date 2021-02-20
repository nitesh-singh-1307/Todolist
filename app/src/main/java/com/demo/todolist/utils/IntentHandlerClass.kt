package com.demo.todolist.utils

import android.content.Intent
import android.view.View
import androidx.core.content.ContextCompat.startActivity
import com.demo.todolist.ui.activity.LoginActivity

class IntentHandlerClass {
    fun onClickFriend(view: View) {
        val intent = Intent(view.context, LoginActivity::class.java)
        view.context.startActivity(intent);
    }
}