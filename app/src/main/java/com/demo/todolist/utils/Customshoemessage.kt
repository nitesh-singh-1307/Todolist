package com.demo.todolist.utils

import android.content.Context
import android.widget.Toast

object Customshoemessage {
    fun CustomToast(mcontext: Context, mstr_message: String) {
        Toast.makeText(mcontext, mstr_message, Toast.LENGTH_LONG).show()
    }

     fun toast(mcontext: Context ,text: String) {
        var toast: Toast? = null
        toast?.cancel()
        toast = Toast.makeText(mcontext, text, Toast.LENGTH_SHORT)
        toast?.show()
    }


}