package com.smarttasks.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import com.smarttasks.model.Task
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.smarttasks.R
import com.smarttasks.constant.Extensions.orNA
import com.smarttasks.ui.theme.boldStyle
import com.smarttasks.ui.theme.regularStyle
import com.smarttasks.utils.TaskSharedPreference

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskDetailsScreen(task: Task, navigateBack: () -> Unit) {
    val context = LocalContext.current
    val initialColor = colorResource(id = R.color.app_red)
    val greenColor = colorResource(id = R.color.app_green)

    var showDialog by remember { mutableStateOf(false) }
    var commentText by remember { mutableStateOf(TaskSharedPreference.getComment(context, task.id) ?: "") }

    var currentTask by remember {
        mutableStateOf(
            task.copy(
                isResolved = TaskSharedPreference.getIsResolved(
                    context = context,
                    task.id
                )
            )
        )
    }

    //Update text color after pressing resolved or cant resolve buttons
    val textColor by remember(currentTask) {
        derivedStateOf {
            if (currentTask.isResolved == true) greenColor
            else initialColor
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        stringResource(R.string.task_details),
                        color = Color.White,
                        fontFamily = FontFamily(Font(R.font.amsiprobold))
                    )
                },
                navigationIcon = {
                    IconButton(onClick = navigateBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                            contentDescription = stringResource(R.string.go_back),
                            tint = Color.White,
                            modifier = Modifier.size(32.dp)
                        )
                    }
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = colorResource(id = R.color.app_yellow)
                )
            )
        }, content = { innerPadding ->

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(colorResource(id = R.color.app_yellow))
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TaskDetailsCard(
                    task = currentTask,
                    textColor = textColor,
                    innerPadding = innerPadding,
                    commentText = commentText
                )
                ResolutionSection(
                    task = currentTask,
                    onResolve = {
                        currentTask = currentTask.copy(isResolved = true)
                        TaskSharedPreference.saveIsResolved(context, task.id, true)
                        showDialog = true
                    },
                    onCantResolve = {
                        currentTask = currentTask.copy(isResolved = false)
                        TaskSharedPreference.saveIsResolved(context, task.id, false)
                        showDialog = true
                    },
                    textColor = textColor
                )
            }
        })

    if (showDialog) {
        AddCommentDialog(
            commentText= commentText,
            onCommentChange = { commentText = it },
            onDismiss = {
                showDialog = false
                commentText = ""
                },
            onConfirm = {
                showDialog = false
                if (commentText.isNotEmpty()) {
                  TaskSharedPreference.saveComment(context,task.id,commentText)
                }
            }
        )
    }
}

@Composable
private fun TaskDetailsCard(task: Task, textColor: Color, innerPadding: PaddingValues,commentText: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(innerPadding),
        shape = RoundedCornerShape(8.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(colorResource(id = R.color.app_yellow))
        ) {
            Image(
                painter = painterResource(id = R.drawable.task_details),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.matchParentSize()
            )
            Column(
                modifier = Modifier.padding(top = 32.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                TaskItemReusable(task, textColor,hasComment = if(commentText.isNotEmpty()) true else false )
                HorizontalDivider(
                    color = Color.LightGray.copy(alpha = 0.5f),
                    thickness = 1.dp
                )
                Text(
                    text = task.Description.orNA(),
                    modifier = Modifier.padding(10.dp),
                    style = regularStyle
                )
                HorizontalDivider(
                    color = Color.LightGray.copy(alpha = 0.5f),
                    thickness = 1.dp
                )
                if(commentText.isNotEmpty()){
                    Text(
                        text = commentText,
                        modifier = Modifier.padding(10.dp),
                        style = regularStyle
                    )
                    HorizontalDivider(
                        color = Color.LightGray.copy(alpha = 0.5f),
                        thickness = 1.dp
                    )
                }

                Text(
                    text = when (task.isResolved) {
                        true -> stringResource(R.string.resolved)
                        false -> stringResource(R.string.can_t_resolve)
                        else -> stringResource(R.string.unresolved)
                    },
                    color = textColor,
                    modifier = Modifier.padding(10.dp),
                    style = boldStyle
                )
            }
        }
    }
}

@Composable
private fun ResolutionSection(
    task: Task,
    onResolve: () -> Unit,
    onCantResolve: () -> Unit,
    textColor: Color
) {
    if (task.isResolved != null) {
        val imageRes =
            if (task.isResolved == true) R.drawable.sign_resolved else R.drawable.unresolved_sign
        val contentDescRes = if (task.isResolved == true) R.string.resolved else R.string.unresolved
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = stringResource(id = contentDescRes),
            modifier = Modifier
                .padding(top = 16.dp)
                .size(120.dp)
        )
    } else {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = onResolve,
                colors = ButtonDefaults.buttonColors(colorResource(id = R.color.app_green)),
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(4.dp)
            ) {
                Text(
                    text = stringResource(R.string.resolve),
                    color = Color.White,
                    style = boldStyle
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(
                onClick = onCantResolve,
                colors = ButtonDefaults.buttonColors(textColor),
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(4.dp)
            ) {
                Text(
                    text = stringResource(R.string.can_t_resolve),
                    color = Color.White,
                    style = boldStyle
                )
            }
        }
    }
}

@Preview
@Composable
private fun Prev_TaskDetailsScreen() {
    TaskDetailsScreen(task = Task("AAAA", "2024-08-25", 5, "2024-08-26", "Title", "999", null), {})

}