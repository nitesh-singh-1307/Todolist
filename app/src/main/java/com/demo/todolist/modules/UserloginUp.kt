package com.demo.todolist.modules

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Userslogin")
data class UserloginUp(
    @PrimaryKey(autoGenerate = true)
    var user_id : Int,
    var user_name : String,
    var user_mobile : String
)
