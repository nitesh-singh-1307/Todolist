package com.demo.todolist.ui.activity

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.demo.todolist.R
import com.demo.todolist.databinding.ActivityRagistrationBinding
import com.demo.todolist.utils.IntentHandlerClass
import com.demo.todolist.viewmodel.SigninViewModel

class RagistrationActivity : AppCompatActivity() {
    private lateinit var signinViewModel: SigninViewModel
    private lateinit var ragistrationBinding: ActivityRagistrationBinding
    val list_priority = mutableListOf("HR", "Mobile Team", "Web Team")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ragistrationBinding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_ragistration
        )

        signinViewModel = SigninViewModel(application)
        ragistrationBinding.signinviewmodel = signinViewModel
        val handlers = IntentHandlerClass()
        ragistrationBinding.intentHandler = handlers
        ragistrationBinding.lifecycleOwner = this
        ragistrationBinding.editUsertype.setAdapter(
            ArrayAdapter(
                this,
                android.R.layout.select_dialog_item,
                list_priority
            )
        )

        ragistrationBinding.editUsertype.setText("HR")

    }
}