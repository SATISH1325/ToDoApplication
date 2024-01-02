package com.logic.satish.todoapplication.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    val taskId:Int = 0,
    val taskTitle:String,
    val taskDescription:String,
    val taskDateTime:String
)
