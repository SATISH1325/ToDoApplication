package com.logic.satish.todoapplication.viewmodel

import android.icu.text.CaseMap.Title
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.logic.satish.todoapplication.model.TaskEntity
import com.logic.satish.todoapplication.repository.TaskRepository
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.util.Date

class TaskViewModel(private val repository : TaskRepository) : ViewModel() {

    val tasks : LiveData<List<TaskEntity>> = repository.allTasks

    fun addTask(taskTitle: String, taskDescription:String, taskDateTime:String){
        viewModelScope.launch {
            val newTask = TaskEntity(
                taskTitle = taskTitle,
                taskDescription = taskDescription,
                taskDateTime = taskDateTime
            )
            repository.addNewTask(newTask)
        }
    }


}