package com.smarttasks.repository

import com.smarttasks.api.TaskListApi
import com.smarttasks.model.Tasks
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TaskListRepository(private val taskListApi: TaskListApi)   {
    suspend fun getTaskList() : Flow<Result<Tasks>> {
       return flow{
           try {
               val response = taskListApi.getTaskList()
               emit(Result.Success(response))
           } catch (e: Exception) {
               emit(Result.Error(e))
           }
        }
    }
}