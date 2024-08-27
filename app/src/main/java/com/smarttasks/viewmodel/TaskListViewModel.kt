package com.smarttasks.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.smarttasks.repository.TaskListRepository
import com.smarttasks.model.Tasks
import com.smarttasks.repository.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskListViewModel @Inject constructor(private val taskListRepository: TaskListRepository) :
    ViewModel() {

    private val _taskList = MutableStateFlow<Result<Tasks?>>(Result.Loading)
    var taskList: StateFlow<Result<Tasks?>> = _taskList.asStateFlow()

    fun getTaskList() {
        viewModelScope.launch(Dispatchers.IO) {
            taskListRepository.getTaskList().collect { response ->
                _taskList.value = response
            }
        }
    }
}