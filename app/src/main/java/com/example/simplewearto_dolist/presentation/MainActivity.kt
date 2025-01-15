package com.example.simplewearto_dolist.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.wear.compose.foundation.lazy.rememberScalingLazyListState
import androidx.wear.compose.material.PositionIndicator
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.TimeText
import androidx.wear.compose.material.Vignette
import androidx.wear.compose.material.VignettePosition
import androidx.wear.tooling.preview.devices.WearDevices
import com.example.simplewearto_dolist.presentation.composables.TasksList
import com.example.simplewearto_dolist.presentation.model.Task
import com.example.simplewearto_dolist.presentation.theme.SimpleWearToDoListTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setTheme(android.R.style.Theme_DeviceDefault)

        setContent {
            SimpleWearToDoListTheme {
                TaskApp()
            }
        }
    }
}

@Composable
fun TaskApp() {
    val tasks = remember {
        mutableStateListOf(
            Task("Buy vegetables"),
            Task("Read a book"),
            Task("Practice sports"),
            Task("Cook dinner"),
            Task("Clean the house")
        )
    }

    TaskScreen(
        tasks = tasks,
        onCheckedChange = { taskIndex, isCompleted ->
            tasks[taskIndex] = tasks[taskIndex].copy(isCompleted = isCompleted)
        }
    )
}

@Composable
fun TaskScreen(
    tasks: List<Task>,
    onCheckedChange: (Int, Boolean) -> Unit
) {
    val scrollState = rememberScalingLazyListState()
    val isScrolling by remember { derivedStateOf { scrollState.isScrollInProgress } }

    Scaffold(
        timeText = {
            if (!isScrolling) {
                TimeText()
            }
        },
        vignette = { Vignette(VignettePosition.TopAndBottom) },
        positionIndicator = { PositionIndicator(scalingLazyListState = scrollState) }
    ) {
        TasksList(
            tasks = tasks,
            onCheckedChange = onCheckedChange,
            scrollState = scrollState
        )
    }
}

@Preview(device = WearDevices.SMALL_ROUND, showSystemUi = true)
@Composable
fun DefaultPreview() {
    SimpleWearToDoListTheme {
        TaskApp()
    }
}