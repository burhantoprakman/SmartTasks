package com.smarttasks.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.smarttasks.R
import com.smarttasks.model.Task
import com.smarttasks.utils.AppUtil
import java.time.LocalDate

@Composable
fun MainScreen(navController: NavController, tasks: List<Task>) {
    var currentDate by remember { mutableStateOf(LocalDate.now()) }
    var filteredTasksList by remember { mutableStateOf(tasks) }

    //Filter tasks by date
    filteredTasksList = AppUtil.filterByDate(tasks, currentDate)

    //If day is today, then set title as Today otherwise set date
    val title =
        if (AppUtil.isToday(currentDate)) stringResource(R.string.today) else AppUtil.dateFormat(
            currentDate
        )

    Scaffold(
        topBar = {
            MainScreenTopAppBar(
                onPreviousDay = {
                    currentDate = currentDate.minusDays(1)
                    filteredTasksList = AppUtil.filterByDate(tasks, currentDate)
                },
                onNextDay = {
                    currentDate = currentDate.plusDays(1)
                    filteredTasksList = AppUtil.filterByDate(tasks, currentDate)
                },
                title = title
            )
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .background(color = colorResource(id = R.color.app_yellow))
                    .fillMaxSize()
            ) {

                if (filteredTasksList.isEmpty()) {
                    //If there is no task for specific day then show NoTaskScreen
                    NoTaskScreen()
                } else {
                    TaskListScreen(navController = navController, tasks = filteredTasksList)
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreenTopAppBar(
    onPreviousDay: () -> Unit,
    onNextDay: () -> Unit,
    title: String
) {
    CenterAlignedTopAppBar(
        title = {},
        navigationIcon = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colorResource(id = R.color.app_yellow))
                    .padding(vertical = 8.dp),
            ) {
                IconButton(onClick = onPreviousDay) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                        contentDescription = stringResource(
                            R.string.previous_day_day_is,
                            title
                        ),
                        tint = Color.White,
                        modifier = Modifier.size(32.dp)
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = title,
                    fontFamily = FontFamily(Font(R.font.amsiprobold)),
                    fontSize = 20.sp,
                    color = Color.White,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = onNextDay) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                        contentDescription = stringResource(R.string.next_day_day_is, title),
                        tint = Color.White,
                        modifier = Modifier.size(32.dp)
                    )
                }
            }
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = colorResource(id = R.color.app_yellow)
        )
    )
}

@Preview
@Composable
private fun Prev_MainScreen() {
    MainScreen(navController = rememberNavController(), tasks = listOf())

}
