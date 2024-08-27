package com.smarttasks.ui.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.smarttasks.R
import com.smarttasks.model.Task

@Composable
fun TaskItemScreen(task: Task, hasComment: Boolean, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, end = 8.dp, start = 8.dp)
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(5.dp)
    ) {
        TaskItemReusable(task, colorResource(id = R.color.app_red), hasComment)
    }
}

@Preview(showBackground = true)
@Composable
private fun Prev_TaskItem() {
    TaskItemScreen(
        task = Task("AAAA", "2024-08-25", 5, "2024-08-26", "Title", "", null),
        true
    ){}

}

