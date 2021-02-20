package com.demo.todolist.roomdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.demo.todolist.modules.Signup
import com.demo.todolist.modules.Todolist
import com.demo.todolist.modules.UserloginUp

@Database(entities = [Todolist::class, Signup::class , UserloginUp::class], version = 1, exportSchema = false)
abstract class Todolistdatabase : RoomDatabase() {
    abstract fun Todolistdao(): Todolistdao
    abstract fun UsersDao(): UsersDao

    companion object {
        @Volatile
        private var instance: Todolistdatabase? = null
        fun getInstance(context: Context): Todolistdatabase? {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    Todolistdatabase::class.java, "todolist"
                )
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return instance
        }
    }

}