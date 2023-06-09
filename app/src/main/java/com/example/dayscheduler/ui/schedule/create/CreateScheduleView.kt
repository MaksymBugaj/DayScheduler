package com.example.dayscheduler.ui.schedule.create

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.dayscheduler.data.db.entity.task.TaskEntity
import com.example.dayscheduler.ui.theme.Teal200
import com.example.dayscheduler.ui.theme.lightGrey
import com.example.dayscheduler.ui.util.TAG

@Composable
fun CreateScheduleView (
    viewModel: CreateScheduleViewModel,
    onCreateTaskClick: () -> Unit,
    tasksSaved: () -> Unit
){
    val tasks by viewModel.tasks.observeAsState(initial = emptyList())
    val selectedTasks by viewModel.selectedTasks.observeAsState(initial = emptyList())
    val showCreateSchedule by viewModel.createScheduleClicked.observeAsState()
    val onSaveReady by viewModel.scheduleSaved.observeAsState(initial = false)

    if (onSaveReady) {
        viewModel.setScheduleSaved(false)
        tasksSaved()
    }
    val showConfirmDialog = remember {
        mutableStateOf(false)
    }
    val fabAlpha = remember {
        mutableStateOf(0f)
    }
    if(selectedTasks.isNotEmpty()) {
        fabAlpha.value = 1f
    }
    else {
        fabAlpha.value = 0f
    }

//    if(showConfirmDialog.value) {
//        Log.d(TAG.commonTag," TESTING")
//        CreateScheduleViewFromTasks(viewModel, onDismiss = {
//            showConfirmDialog.value = false
//        })
//    }

    Scaffold(floatingActionButtonPosition = FabPosition.End, floatingActionButton = {
        FloatingActionButtonComplete(
        onClick = {
            Log.d(TAG.commonTag,"onAddClick")
            //showConfirmDialog.value = true
            viewModel.save()
            fabAlpha.value = 0f
        }, modifier = Modifier.alpha(fabAlpha.value))
    }
    ) { paddingValues ->
        if(showConfirmDialog.value) {
            Log.d(TAG.commonTag,"show Confirm dialog")
            ConfirmScheduleView(viewModel = viewModel)
        } else {
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = 8.dp,
                    end = 8.dp,
                    start = 8.dp,
                    bottom = paddingValues.calculateBottomPadding()
                ),
                horizontalAlignment = Alignment.CenterHorizontally) {
                if (tasks.isEmpty()) {
                    ShowCreateTaskView(onCreateTaskClick)
                } else {
                    CreateTaskCardView(onCreateTaskClick)
                    CreateSchedule(tasks, viewModel)
                }
            }
        }
    }

}

@Composable
fun ShowCreateTaskView(
    onCreateTaskClick: () -> Unit
) {
    Card(elevation = 4.dp, modifier = Modifier
        .wrapContentHeight()
        .fillMaxWidth()
        .padding(8.dp)) {
        Text(text = "There are no tasks, go and create one!", modifier = Modifier
            .padding(8.dp)
            .clickable {
                onCreateTaskClick()
            }, textAlign = TextAlign.Center)
    }
}

@Composable
fun CreateTaskCardView(onCreateTaskClick: () -> Unit) {
    Card(elevation = 4.dp, modifier = Modifier
        .wrapContentHeight()
        .fillMaxWidth()
        .padding(8.dp)) {
        Text(text = "Need more tasks?\nGo and create one!", modifier = Modifier
            .padding(8.dp)
            .clickable {
                onCreateTaskClick()
            }, textAlign = TextAlign.Center)
    }
}

@Composable
fun CreateSchedule(tasks: List<TaskItem>, viewModel: CreateScheduleViewModel) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(10.dp)) {
        Text(text = "Choose tasks to your schedule", modifier = Modifier.padding(top = 10.dp, bottom = 10.dp))
        LazyColumn(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(2.dp), state = rememberLazyListState(),
            content = {
                items(tasks) { task ->
                    TasksView(task = task, viewModel)
                }
            })
    }
}

@Composable
fun FloatingActionButtonComplete(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: ImageVector = Icons.Filled.Add
) {
    FloatingActionButton(onClick = { onClick() }, backgroundColor = lightGrey, contentColor = Teal200, modifier = modifier) {
        Icon(icon, "")
    }
}

@Composable
fun TasksView(task: TaskItem, createScheduleViewModel: CreateScheduleViewModel) {
    var backgroundColor by remember {
        if(task.isSelected.value)mutableStateOf(Teal200) else mutableStateOf(Color.White)
    }
    Card(elevation = 4.dp, modifier = Modifier
        .padding(8.dp)
        .selectable(selected = task.isSelected.value, onClick = {
            task.toggle()
            if (task.isSelected.value) {
                createScheduleViewModel.addSelectedTask(task)
            } else {
                createScheduleViewModel.removeSelectedTask(task)
            }
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



data class TaskItem(
    val id: Int,
    val name: String,
    val additionalInfo: String?,
    var isSelected: MutableState<Boolean>,
    val isActive: Boolean
) {
    fun toggle() {
        Log.d(TAG.commonTag," toogle: ${isSelected.value}")
        isSelected.value = !isSelected.value
        Log.d(TAG.commonTag," toogled: ${isSelected.value}")
    }

    constructor(taskEntity: TaskEntity): this (
        id = taskEntity.id, name = taskEntity.name, additionalInfo = taskEntity.additionalInfo, isSelected = mutableStateOf(false), isActive = true
            )
}

