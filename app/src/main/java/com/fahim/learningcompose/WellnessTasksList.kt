package com.fahim.learningcompose

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.fahim.learningcompose.ui.WellnessTaskItem
import com.fahim.learningcompose.viewmodel.getWellnessTasks


@Composable
fun WellnessTasksList(
    modifier: Modifier = Modifier,
    list: List<WellnessTask> = remember { getWellnessTasks() },
    onCloseTask: (WellnessTask) -> Unit,
    onCheckedTask: (WellnessTask, Boolean) -> Unit
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(items = list, key = { task -> task.id }) { task ->
            WellnessTaskItem(taskName = task.label, onClose = { onCloseTask },
                onCheckedChange = { checked -> onCheckedTask(task, checked) })
        }
    }
}