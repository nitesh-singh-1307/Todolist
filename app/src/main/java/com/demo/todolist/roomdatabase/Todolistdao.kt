package com.demo.todolist.roomdatabase

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery
import com.demo.todolist.modules.Todolist

@Dao
interface Todolistdao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTodolist(todolist: Todolist)

    @Query("SELECT * FROM  todolist")
    fun getTodolist(): LiveData<List<Todolist>>

    @Query("DELETE FROM Todolist WHERE todoid = :todouid")
    fun deleteByUserId(todouid: Int)

    @Query("SELECT * FROM Todolist WHERE todoid = :todouid")
    fun selecttodoitemparticuler(todouid: Int) : LiveData<List<Todolist>>

    @Query("SELECT * FROM Todolist WHERE todoid = :todouid")
    fun selecttodoitemparticuler2(todouid: Int) : LiveData<Todolist>


    @Query("UPDATE Todolist SET work_name = :str_name  , description = :str_description , date =:str_date WHERE todoid LIKE :todouid")
    fun updateTodolist(
        todouid: Int,
        str_name: String,
        str_description: String,
        str_date: String
    ): Int

}