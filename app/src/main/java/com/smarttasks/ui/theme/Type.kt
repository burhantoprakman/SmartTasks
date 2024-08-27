package com.smarttasks.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.smarttasks.R

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
)
val boldStyle = TextStyle(
        fontFamily = FontFamily(Font(R.font.amsiprobold)),
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp
)
val regularStyle = TextStyle(
        fontFamily = FontFamily(Font(R.font.amsiproregular)),
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp)