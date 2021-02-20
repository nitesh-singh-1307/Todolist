package com.demo.todolist

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.AbsListView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.demo.todolist.databinding.ActivityMainBinding
import com.demo.todolist.modules.Todolist
import com.demo.todolist.ui.activity.AddTodolistActivity
import com.demo.todolist.ui.activity.EditTodoListActivity
import com.demo.todolist.ui.adapter.TodolistAdapter
import com.demo.todolist.utils.Customshoemessage.CustomToast
import com.demo.todolist.utils.Customshoemessage.toast
import com.demo.todolist.viewmodel.TodolistViewmodel
import com.example.practicaleinterview.Utils.SwipeHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.*


class MainActivity : AppCompatActivity()  {
    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var todolistViewmodel: TodolistViewmodel
    private lateinit var todolistAdapter: TodolistAdapter
    var todolist: List<Todolist>? = null
    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        todolistAdapter = TodolistAdapter()
        mainBinding.recyItem.adapter = todolistAdapter
        todolistViewmodel = ViewModelProvider(this@MainActivity).get(TodolistViewmodel::class.java)
        todolistViewmodel = TodolistViewmodel(application)
        todolistViewmodel?.getallTodolist()?.observe(this, Observer {
            it.let {
                todolistAdapter?.list = it
                todolist = it
                Log.d("check_roomdatabase", "****************" + it)
            }
        })


        mainBinding.flotAddtodolist.setOnClickListener {
            CustomToast(this@MainActivity, "test.....")
            var intent = Intent(this@MainActivity, AddTodolistActivity::class.java)
            startActivity(intent)
        }


        val itemTouchHelper = ItemTouchHelper(object : SwipeHelper(mainBinding.recyItem) {
            override fun instantiateUnderlayButton(position: Int): List<UnderlayButton> {
                var buttons = listOf<UnderlayButton>()
                val deleteButton = deleteButton(position)
                val markAsUnreadButton = markAsUnreadButton(position)
                val archiveButton = archiveButton(position)
                buttons = listOf(deleteButton, markAsUnreadButton)

                return buttons
            }
        })

        itemTouchHelper.attachToRecyclerView(mainBinding.recyItem)

    }

    private fun setUpRecyclerview() {
        mainBinding.recyItem.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )
//        mainBinding.recyItem.layoutManager = LinearLayoutManager(this)

    }


    private fun deleteButton(position: Int): SwipeHelper.UnderlayButton {
        return SwipeHelper.UnderlayButton(
            this,
            "Delete",
            14.0f,
            android.R.color.holo_red_light,
            object : SwipeHelper.UnderlayButtonClickListener {
                override fun onClick() {
                    todolistViewmodel?.deleteTodolist(todolist?.get(position)!!.todoid)
                    todolistAdapter!!.notifyItemRemoved(position)

                }
            })
    }

    private fun markAsUnreadButton(position: Int): SwipeHelper.UnderlayButton {
        return SwipeHelper.UnderlayButton(
            this,
            "Edit",
            14.0f,
            android.R.color.holo_green_light,
            object : SwipeHelper.UnderlayButtonClickListener {
                override fun onClick() {

                    coroutineScope.launch(Dispatchers.Main) {

                        var intent =
                            Intent(this@MainActivity, EditTodoListActivity::class.java)
                        intent.putExtra("todoid", todolist?.get(position) as Todolist)
                        startActivity(intent)

                    }


                }
            })
    }

    private fun archiveButton(position: Int): SwipeHelper.UnderlayButton {
        return SwipeHelper.UnderlayButton(
            this,
            "Archive",
            14.0f,
            android.R.color.holo_blue_light,
            object : SwipeHelper.UnderlayButtonClickListener {
                override fun onClick() {
                }
            })
    }


}