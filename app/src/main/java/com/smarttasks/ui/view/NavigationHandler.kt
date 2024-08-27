package com.smarttasks.ui.view

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.smarttasks.model.Task
import com.smarttasks.model.Tasks
import com.smarttasks.repository.Result
import com.smarttasks.viewmodel.TaskListViewModel
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

@Composable
fun NavigationHandler() {
    val navController = rememberNavController()
    val taskListViewModel: TaskListViewModel = hiltViewModel<TaskListViewModel>()

    //Get task list from viewmodel
    val tasks by taskListViewModel.taskList.collectAsState()

    NavHost(navController = navController, startDestination = "splashScreen") {
        composable(
            "taskDetailsScreen/{task}",
            arguments = listOf(navArgument("task") { type = NavType.StringType })
        )
        { backStackEntry ->
            /**
             * Convert Task class to json to pass in arguments
             * Decode the url because task description has url.
             * **/
        val task = try {
            val taskJson = backStackEntry.arguments?.getString("task")
            val decodedTaskJson = URLDecoder.decode(taskJson, StandardCharsets.UTF_8.toString())
            Gson().fromJson(decodedTaskJson, Task::class.java)

        } catch (e:Exception){
            //Toast.makeText(LocalContext.current,"Exception : $e",Toast.LENGTH_SHORT).show()
           null
        }
            if (task != null) {
                TaskDetailsScreen(task = task) { navController.popBackStack() }
            }
        }

        composable("mainScreen") {
            when (tasks) {
                is Result.Success -> {
                    (tasks as Result.Success<Tasks?>).data?.tasks?.let { it1 ->
                        MainScreen(
                            navController = navController,
                            tasks = it1
                        )
                    }
                }

                is Result.Error -> {
                    val error = (tasks as Result.Error).exception
                    Toast.makeText(
                        LocalContext.current,
                        "Error message: $error",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                is Result.Loading -> {}
            }

        }

        composable("splashScreen") {
            SplashScreen(viewmodel = taskListViewModel, onTimeout = {
                navController.navigate("mainScreen") {
                    popUpTo("splashScreen") { inclusive = true }
                }

            })
        }
    }

}