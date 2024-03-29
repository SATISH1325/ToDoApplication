package com.logic.satish.todoapplication.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.logic.satish.todoapplication.model.TaskEntity
import com.logic.satish.todoapplication.repository.TaskDao

@Database(entities = [TaskEntity::class], version = 1)
abstract class TaskDatabase : RoomDatabase() {

    abstract fun taskDao() : TaskDao

    companion object {

        @Volatile
        private var INSTANCE : TaskDatabase? = null

        fun getDatabase(context: Context) : TaskDatabase{

            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext,TaskDatabase::class.java,"task_database").build()
                INSTANCE = instance
                instance
            }
        }
    }
}