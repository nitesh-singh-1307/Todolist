package com.demo.todolist.ui.activity

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import com.demo.todolist.R
import com.demo.todolist.databinding.ActivityEditTodoListBinding
import com.demo.todolist.modules.Todolist
import com.demo.todolist.viewmodel.UpdateTodoListViewModel
import java.text.SimpleDateFormat
import java.util.*


class EditTodoListActivity : AppCompatActivity() {
    lateinit var editTodoListBinding: ActivityEditTodoListBinding
    lateinit var updateTodoListViewModel: UpdateTodoListViewModel
    val myCalendar = Calendar.getInstance()
    val list_priority = mutableListOf("high", "low", "medium")
    var todolist_serilizeable : Todolist ? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        editTodoListBinding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_edit_todo_list
        )
        editTodoListBinding.editProvarity.setAdapter(
            ArrayAdapter(
                this,
                android.R.layout.select_dialog_item,
                list_priority
            )
        )

        todolist_serilizeable  = intent.getSerializableExtra("todoid") as? Todolist

        updateTodoListViewModel = UpdateTodoListViewModel(application)
        editTodoListBinding.uptodoviewmodel = updateTodoListViewModel
        editTodoListBinding.todomodel = todolist_serilizeable
        editTodoListBinding.lifecycleOwner = this


        val date =
            OnDateSetListener { view, year, monthOfYear, dayOfMonth -> // TODO Auto-generated method stub
                myCalendar[Calendar.YEAR] = year
                myCalendar[Calendar.MONTH] = monthOfYear
                myCalendar[Calendar.DAY_OF_MONTH] = dayOfMonth
                updateLabel()
            }

        editTodoListBinding.editDate.setOnClickListener {
            DatePickerDialog(
                this@EditTodoListActivity, date, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        updateTodoListViewModel.updateValue.observe(this, androidx.lifecycle.Observer {
            if (it == -1) {
            } else {
                finish()
                //updateTodoListViewModel.clearUpdate()
                Log.e("<<>>", "<<------notUpdated-2-<<$it>>")
            }

        })

    }

    private fun updateLabel() {
        val myFormat = "MM/dd/yy" //In which you need put here
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        editTodoListBinding.editDate.setText(sdf.format(myCalendar.time))
    }


    companion object {
        @BindingAdapter("finishEditActivity")
        @JvmStatic
        fun finishEditActivity(view: View, isFinished: Boolean) {
            if (isFinished) {

            }

        }


    }
}