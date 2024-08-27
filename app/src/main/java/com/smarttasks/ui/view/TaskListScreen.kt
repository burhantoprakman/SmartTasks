package com.smarttasks.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.gson.Gson
import com.smarttasks.R
import com.smarttasks.model.Task
import com.smarttasks.utils.TaskSharedPreference
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun TaskListScreen(navController: NavController, tasks: List<Task>) {
    val context = LocalContext.current


    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.app_yellow))
    ) {
        /**
         * Comparator to sort tasks by priority(highest first) and due date(earliest first)
         * If due date is null then set date to MAX to push it to end of the list.
         **/
        val taskComparator = compareByDescending<Task> { it.Priority ?: Int.MIN_VALUE }
            .thenBy {
                it.DueDate?.let { dueDate ->
                    LocalDate.parse(
                        dueDate,
                        DateTimeFormatter.ofPattern("yyyy-MM-dd")
                    )
                } ?: LocalDate.MAX
            }

        // Sort the task list based on the comparator
        val sortedTasks = tasks.sortedWith(taskComparator)

        items(sortedTasks) { taskItem ->
            TaskItemScreen(
                task = taskItem,
                hasComment = if (TaskSharedPreference.getComment(context, taskItem.id)
                        ?.isNotEmpty() == true
                ) true else false
            ) {
                /**
                 * Convert Task class to json to pass in arguments
                 * Encode the url because task description has url.
                 * **/
                val taskJson = Gson().toJson(taskItem)
                val encodedTaskJson = URLEncoder.encode(taskJson, StandardCharsets.UTF_8.toString())

                navController.navigate("taskDetailsScreen/$encodedTaskJson")
            }

        }
    }
}

@Preview
@Composable
private fun Prev_TaskListScreen() {
    val taskList = listOf(
        Task("AAAA", "2024-08-25", 5, "2024-08-26", "Title", "", null),
        Task("AAAA", "2024-08-25", 5, "2024-08-26", "Title", "", null)
    )

    TaskListScreen(navController = rememberNavController(), tasks = taskList)
}