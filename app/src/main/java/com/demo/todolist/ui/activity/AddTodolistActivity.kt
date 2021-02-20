package com.demo.todolist.ui.activity

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.demo.todolist.R
import com.demo.todolist.databinding.ActivityAddTodolistBinding
import com.demo.todolist.viewmodel.TodolistViewmodel
import java.text.SimpleDateFormat
import java.util.*


class AddTodolistActivity : AppCompatActivity() {
    private lateinit var todolistActivitybinding: ActivityAddTodolistBinding
    private lateinit var todolistviewModel: TodolistViewmodel
    val myCalendar = Calendar.getInstance()
    val list_priority = mutableListOf("high", "low", "medium")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        todolistActivitybinding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_add_todolist
        )
        todolistActivitybinding.editProvarity.setAdapter(
            ArrayAdapter(
                this,
                android.R.layout.select_dialog_item,
                list_priority))

        todolistActivitybinding.editProvarity.setText("high")
        todolistviewModel = TodolistViewmodel(application)
        todolistActivitybinding.addviewmodeltodolist = todolistviewModel
        val date =
            OnDateSetListener { view, year, monthOfYear, dayOfMonth -> // TODO Auto-generated method stub
                myCalendar[Calendar.YEAR] = year
                myCalendar[Calendar.MONTH] = monthOfYear
                myCalendar[Calendar.DAY_OF_MONTH] = dayOfMonth
                updateLabel()
            }

        todolistActivitybinding.editDate.setOnClickListener {
            DatePickerDialog(
                this@AddTodolistActivity, date, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)
            ).show()

        }

    }

    private fun updateLabel() {
        val myFormat = "MM/dd/yy" //In which you need put here
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        todolistActivitybinding.editDate.setText(sdf.format(myCalendar.getTime()))
    }


}