package com.example.dayscheduler.ui.schedule.current

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.dayscheduler.ui.theme.schedulerColors
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Commit
import androidx.compose.material.icons.filled.Create
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import com.example.dayscheduler.ui.schedule.create.FloatingActionButtonComplete
import com.example.dayscheduler.ui.schedule.create.TaskItem
import com.example.dayscheduler.ui.theme.Teal200

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CurrentScheduleView(
    viewModel: CurrentScheduleViewModel,
    onAddTasksClick: () -> Unit
) {

    val tasks by viewModel.tasks.observeAsState(emptyList())
    val allTasksFinished by viewModel.allTasksFinished.observeAsState(initial = false)
    val selectedTasks by viewModel.selectedTasks.observeAsState(emptyList())
    val fabAlpha = remember {
        mutableStateOf(0f)
    }
    if(selectedTasks.isNotEmpty()) {
        fabAlpha.value = 1f
    }
    else {
        fabAlpha.value = 0f
    }

    Scaffold(floatingActionButtonPosition = FabPosition.End, floatingActionButton = {
        FloatingActionButtonComplete(
            onClick = {
                viewModel.markAsCompleted()
            },
            icon = Icons.Filled.Check,
            modifier = Modifier.alpha(fabAlpha.value)
        )
    }) { paddingValues ->
        LazyColumn(
            state = rememberLazyListState(),
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = 8.dp,
                    end = 8.dp,
                    start = 8.dp,
                    bottom = paddingValues.calculateBottomPadding()
                )) {
            stickyHeader {
                TaskForTodayText()
            }
            if(tasks.isEmpty()) {
                if(allTasksFinished) {
                    item {
                        AllTasksFinishedView {
                            viewModel.completeSchedule()
                        }
                    }
                }
                else item {
                    EmptyScheduleText(onAddTasksClick)
                }
            } else {
                items(tasks, key = {item: TaskItem -> item.id }) { item ->
                    TaskRow(item, viewModel)
                }
            }
        }
    }
}

@Composable
fun AllTasksFinishedView(onClick: () -> Unit) {
    Card(
        modifier = Modifier.padding(8.dp),
        elevation = 4.dp,
        shape = RoundedCornerShape(4.dp)
    ) {
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(8.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "All tasks has been finished, you can complete your schedule now!",
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Row(modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
                Button(onClick = { onClick() }) {
                    Text(text = "Complete",
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }
            }

        }
    }

}

@Composable
fun TaskRow(task: TaskItem, viewModel: CurrentScheduleViewModel) {
    var backgroundColor by remember {
        if(task.isSelected.value)mutableStateOf(Teal200) else mutableStateOf(Color.White)
    }
    Card(elevation = 4.dp, modifier = Modifier
        .padding(8.dp)
        .selectable(selected = task.isSelected.value, onClick = {
            task.toggle()
            if (task.isSelected.value) viewModel.addSelectedTask(task)
            else viewModel.removeSelectedTask(task)
            backgroundColor = if (task.isSelected.value) {
                Teal200
            } else Color.White
        })

    ) {
        Column (modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(backgroundColor)
            .padding(8.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.Start){
            Text(text = task.name, fontWeight = FontWeight.Bold,
                color = Color.Black,)
            task.additionalInfo?.let {
                Text(text = it, fontWeight = FontWeight.Bold,
                    color = Color.Black,)
            }

        }

    }
}

@Composable
fun TaskForTodayText() {
    Text(
        text = "Tasks for today",
        modifier = Modifier.padding(
            start = 16.dp,
            top = 16.dp,
            bottom = 16.dp
        ),
        color = MaterialTheme.schedulerColors.defaultFont,
        fontWeight = FontWeight.SemiBold,
        style = MaterialTheme.typography.h5
    )
}

@Composable
fun EmptyScheduleText(onAddTasksClick: () -> Unit) {
    Text(
        text = "no tasks for today, click and select them!",
        modifier = Modifier
            .padding(
                16.dp
            )
            .clickable {
                onAddTasksClick()
            },
        style = TextStyle(
            color = MaterialTheme.colors.primary,
            fontWeight = FontWeight.ExtraBold
        )
    )
}