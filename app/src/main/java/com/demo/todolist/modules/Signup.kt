package com.demo.todolist.modules

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "Users")
data class Signup(
    @PrimaryKey(autoGenerate = true)
    var user_id : Int,
    var user_name : String ,
    var user_email : String,
    var user_mobile : String,
    var user_type : String
) : Serializable
