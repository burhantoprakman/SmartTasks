package com.smarttasks.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.smarttasks.R

@Composable
fun NoTaskScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.empty_screen),
            contentDescription = stringResource(R.string.smiley_face),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 64.dp, vertical = 64.dp),
            contentScale = ContentScale.Crop
        )

        Text(
            text = stringResource(R.string.no_tasks_for_today),
            color = Color.White,
            fontSize = 32.sp,
            fontFamily = FontFamily(Font(R.font.amsiprobold)),
            modifier = Modifier.padding(bottom = 32.dp)
        )
    }
}

@Preview
@Composable
private fun Prev_NoTaskScreen() {
    NoTaskScreen()
}