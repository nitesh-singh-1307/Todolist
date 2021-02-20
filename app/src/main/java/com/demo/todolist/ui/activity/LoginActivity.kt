package com.demo.todolist.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.demo.todolist.MainActivity
import com.demo.todolist.R
import com.demo.todolist.databinding.ActivityLoginBinding
import com.demo.todolist.viewmodel.SigninViewModel
import kotlinx.android.synthetic.main.activity_login.*
import java.util.*


class LoginActivity : AppCompatActivity() {
    lateinit var loginBinding: ActivityLoginBinding
    private lateinit var signinViewModel: SigninViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_login)
        loginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        signinViewModel = SigninViewModel(application)
        loginBinding.signinviewmodel = signinViewModel
        loginBinding.lifecycleOwner = this

        btn_submit.setOnClickListener {
            signinViewModel.selectUser().observe(this, Observer {
                if (Validation()){
                    for (loginuse in it) {
                        if (loginBinding.editPassword.text.toString().trim()
                                .equals(loginuse.user_mobile)
                        ) {
                            Log.d(
                                "checklogin_data",
                                "<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<sssssssssssssss>>>>>>>>>>>>" + loginuse.user_mobile)
                            var intent = Intent(this@LoginActivity, MainActivity::class.java)
                            startActivity(intent)
                            break
                        } else {
                            Log.d(
                                "checklogin_data",
                                "<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<nulldata>>>>>>>>>>>>")

                        }
                    }

                }else{

                    Log.d(
                        "checklogin_data",
                        "<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<validation_issue>>>>>>>>>>>>"
                    )

                }



            })

        }


    }

    private fun Validation(): Boolean {


        if (loginBinding.editUsername.text.toString().trim().equals("")) {
            loginBinding.editUsername.error = "Enter a Valid E-mail Address"
            loginBinding.editUsername.requestFocus()
            return false
        }


        if (loginBinding.editPassword.text.toString().trim().equals("")) {
            loginBinding.editPassword.error = "Enter a Valid E-mail Address"
            loginBinding.editPassword.requestFocus()
            return false
        }

        return true
    }
}