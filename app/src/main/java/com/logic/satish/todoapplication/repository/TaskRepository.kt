package com.logic.satish.todoapplication.repository

import androidx.lifecycle.LiveData
import com.logic.satish.todoapplication.model.TaskEntity

class TaskRepository(private val taskDao: TaskDao) {
    val allTasks:LiveData<List<TaskEntity>> = taskDao.getAllTasks()

    suspend fun addNewTask(task : TaskEntity){
        taskDao.addNewTask(task)
    }

    suspend fun getTaskDetails(taskId:Int) : LiveData<TaskEntity> {
        return taskDao.getTaskDetails(taskId)
    }
}