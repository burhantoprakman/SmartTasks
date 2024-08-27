package com.smarttasks.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.smarttasks.R
import com.smarttasks.ui.theme.boldStyle
import com.smarttasks.ui.theme.regularStyle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddCommentDialog(
    commentText: String,
    onCommentChange: (String) -> Unit,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "Do you want to leave a comment?", color = colorResource(id = R.color.app_red), style = boldStyle) },
        text = {
            Column {
                OutlinedTextField(
                    value = commentText,
                    onValueChange = onCommentChange,
                    placeholder = { Text(text = "Enter your comment", color = Color.Black, style = regularStyle)},
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        containerColor = Color.Transparent,
                        focusedBorderColor = colorResource(id = R.color.app_green),
                        unfocusedBorderColor = colorResource(id = R.color.app_green),
                        cursorColor = colorResource(id = R.color.app_green)
                    ),
                    shape = RoundedCornerShape(8.dp)
                )
            }
        },
        dismissButton = {
            Button(onClick = onDismiss,  colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.app_red)
            )) {
                Text("No")
            }
        },
        confirmButton = {
            Button(onClick = onConfirm,colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.app_green)
            )) {
                Text("Yes")
            }
        },
        containerColor = colorResource(id = R.color.app_yellow)
    )
}

@Preview
@Composable
private fun Prev_AddCommentDialog() {
    AddCommentDialog( "",onCommentChange = {}, onDismiss = { /*TODO*/ }) {
        
    }
    
}