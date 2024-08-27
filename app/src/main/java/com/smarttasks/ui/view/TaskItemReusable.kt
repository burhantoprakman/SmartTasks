package com.smarttasks.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.smarttasks.R
import com.smarttasks.constant.Extensions.orNA
import com.smarttasks.model.Task
import com.smarttasks.ui.theme.boldStyle
import com.smarttasks.ui.theme.regularStyle
import com.smarttasks.utils.AppUtil

@Composable
fun TaskItemReusable(task: Task, textColor: Color, hasComment: Boolean) {
    //Calculate days left
    val daysLeft = if (!task.TargetDate.isNullOrEmpty() && !task.DueDate.isNullOrEmpty()) {
        AppUtil.calculateDaysBetween(task.TargetDate, task.DueDate)
    } else {
        stringResource(id = R.string.no_due_date)
    }
    Column(
        modifier = Modifier
            .padding(10.dp)
            .defaultMinSize(minHeight = 80.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Row(modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = task.Title.orNA(), color = textColor, style = boldStyle,
                modifier = Modifier.weight(1f),
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.width(8.dp))
            if (hasComment) Image(
                painter = painterResource(id = R.drawable.baseline_comment_24),
                contentDescription = ""

            )

        }


        HorizontalDivider(
            color = Color.LightGray.copy(alpha = 0.5f),
            thickness = 1.dp,
            modifier = Modifier.padding(top = 7.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
        ) {
            Text(
                text = stringResource(R.string.due_date),
                color = Color.Gray,
                style = regularStyle,
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = stringResource(R.string.days_left),
                color = Color.Gray,
                style = regularStyle,
            )
        }
        Row(modifier = Modifier.padding(top = 7.dp)) {
            Text(
                text = task.DueDate ?: stringResource(id = R.string.no_due_date),
                color = textColor,
                style = boldStyle,
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = daysLeft,
                color = textColor,
                style = boldStyle,
            )
        }
    }
}

@Preview
@Composable
private fun Prev_TaskItemReusable() {
    val task = Task("AA", "2024-08-25", 5, "2024-08-26", "Title", "999", null)
    TaskItemReusable(task = task, textColor = colorResource(id = R.color.app_red),true)

}