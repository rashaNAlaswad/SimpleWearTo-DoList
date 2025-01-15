package com.example.simplewearto_dolist.presentation.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.wear.compose.material.Checkbox
import androidx.wear.compose.material.Text
import com.example.simplewearto_dolist.presentation.model.Task

@Composable
fun TaskItem(task: Task, onCheckedChange: (Boolean) -> Unit, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = task.isCompleted,
            onCheckedChange = onCheckedChange
        )
        Text(
            text = task.taskName,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Preview()
@Composable
fun TaskItemPreview() {
    val task = Task("Buy vegetables")
    TaskItem(task = task, onCheckedChange = {}, modifier = Modifier)
}