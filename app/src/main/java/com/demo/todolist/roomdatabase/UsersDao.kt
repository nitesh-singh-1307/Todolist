package com.demo.todolist.roomdatabase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.demo.todolist.modules.Signup
import com.demo.todolist.modules.Todolist
import com.demo.todolist.modules.UserloginUp

@Dao
interface UsersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserlist(signup: Signup)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun inserttwoUserlist(userlogin: UserloginUp)

//    @Query("select * from Users")
//    fun getUsersLogin(): LiveData<List<Signup>>

    @Query("select user_id, user_name,user_mobile from Userslogin")
    fun getUsersLogin(): LiveData<List<UserloginUp>>

}