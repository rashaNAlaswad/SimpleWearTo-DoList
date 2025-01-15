package com.example.simplewearto_dolist.presentation.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.wear.compose.foundation.lazy.ScalingLazyColumn
import androidx.wear.compose.foundation.lazy.ScalingLazyListState
import androidx.wear.compose.foundation.lazy.items
import com.example.simplewearto_dolist.presentation.model.Task

@Composable
fun TasksList(
    tasks: List<Task>,
    onCheckedChange: (Int, Boolean) -> Unit,
    scrollState: ScalingLazyListState,
    modifier: Modifier = Modifier
) {
    ScalingLazyColumn(
        state = scrollState,
        modifier = modifier
    ) {
        items(tasks) { task ->
            TaskItem(task = task, onCheckedChange = { onCheckedChange(tasks.indexOf(task), it) })
        }
    }
}