package com.logic.satish.todoapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.logic.satish.todoapplication.repository.TaskRepository
import java.lang.IllegalArgumentException

class TaskViewModelFactory(private val repository: TaskRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(TaskViewModel::class.java)){
            return TaskViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}