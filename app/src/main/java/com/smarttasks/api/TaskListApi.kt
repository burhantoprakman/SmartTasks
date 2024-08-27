package com.smarttasks.api

import com.smarttasks.model.Tasks
import retrofit2.http.GET

interface TaskListApi {
    // This represent the root of the base URL. Because api doesn't have endpoint
    @GET(".")
    suspend fun getTaskList() : Tasks
}