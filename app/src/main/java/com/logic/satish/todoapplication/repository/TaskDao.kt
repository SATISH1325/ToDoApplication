package com.logic.satish.todoapplication.repository

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.logic.satish.todoapplication.model.TaskEntity

@Dao
interface TaskDao {

    @Query("SELECT * FROM tasks")
    fun getAllTasks() : LiveData<List<TaskEntity>>

    @Insert
    suspend fun addNewTask(taskEntity: TaskEntity)

    @Query("SELECT * FROM tasks WHERE taskId ==:taskId")
    fun getTaskDetails(taskId:Int) : LiveData<TaskEntity>
}