package com.smarttasks.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.smarttasks.ui.theme.SmartTasksTheme
import com.smarttasks.ui.view.NavigationHandler
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SmartTasksTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { _ ->
                   NavigationHandler()
                }
            }
        }
    }
}

