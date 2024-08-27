package com.smarttasks.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.smarttasks.R
import com.smarttasks.viewmodel.TaskListViewModel
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(viewmodel: TaskListViewModel, onTimeout: () -> Unit) {
    //Created LaunchedEffect for make api call
    LaunchedEffect(Unit) {
        viewmodel.getTaskList()

        //Put delay to show splash screen and get the data
        delay(2000)
        onTimeout()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.app_yellow))
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth()
                .padding(horizontal = 120.dp, vertical = 160.dp)
        )

        Image(
            painter = painterResource(id = R.drawable.intro_illustration),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(horizontal = 64.dp)
        )
    }
}

@Composable
@Preview(showBackground = true)
fun Prev_SplashScreen(){

}