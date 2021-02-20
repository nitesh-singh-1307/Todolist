package com.demo.todolist.modules

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "todolist")
data class Todolist(
    @PrimaryKey(autoGenerate = true)
    var todoid: Int,
    var work_name: String,
    var description: String,
    var date: String,
    var priority: String,
    var user_id: Int
    ) : Serializable
