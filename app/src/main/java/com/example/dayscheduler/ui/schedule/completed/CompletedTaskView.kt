package com.example.dayscheduler.ui.schedule.completed

import androidx.compose.animation.core.*
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.example.dayscheduler.ui.schedule.create.TaskItem
import com.example.dayscheduler.ui.schedule.current.CurrentScheduleViewModel
import com.example.dayscheduler.ui.theme.Teal200
import com.example.dayscheduler.ui.theme.schedulerColors
import kotlinx.coroutines.delay


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CompletedTaskView(viewModel: CompletedTaskViewModel) {
    val finishedTasks by viewModel.completedTasks.observeAsState(initial = emptyList())
    LazyColumn() {
        stickyHeader {
            CompletedTasksForTodayText()
        }
        items(finishedTasks){ item ->
            TaskRow(task = TaskItem(item), viewModel = viewModel)
        }
    }
}

@Composable
fun TaskRow(task: TaskItem, viewModel: CompletedTaskViewModel) {
    var backgroundColor by remember {
        mutableStateOf(Color.White)
    }
    Card(elevation = 4.dp, modifier = Modifier
        .padding(8.dp)
        .selectable(selected = task.isSelected.value, onClick = {
            task.toggle()
            if (task.isSelected.value) {
                viewModel.addSelectedTask(task)
            } else {
                viewModel.removeSelectedTask(task)
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

@Composable
fun CompletedTasksForTodayText() {
    Text(
        text = "Completed tasks",
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
fun FinishedTasksView(viewModel: CompletedTaskViewModel) {

    val outward = "ACTIVE"
    val returnStatus = "PAST"
    val listOfHehe = mutableListOf<Hehe>()
    for (i in 0..10){
        listOfHehe.add(
            Hehe(
                i,
                if(i%2==0) outward else returnStatus,
                if(i%2==1) outward else returnStatus,
                    )
        )
    }
    for (i in 11..20) {
        listOfHehe.add(
            Hehe(
                i,
                if(i<15) outward else returnStatus,
                if(i<15) outward else returnStatus,
            )
        )
    }
    val activeStatuses = listOf("ACTIVE")
    val pastStatuses = listOf("PAST")
    val activeTickets = listOfHehe.filter {
        activeStatuses.contains(it.outwardStatus) || activeStatuses.contains(it.returnStatus)
    }
    val pastTickets = listOfHehe.filter {
        pastStatuses.contains(it.outwardStatus) && pastStatuses.contains(it.returnStatus)
    }
    var isLoading by remember {
        mutableStateOf(true)
    }
    LaunchedEffect(key1 = true, block = {
        delay(4000)
        isLoading = false
    })

        Column (modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)) {
            LazyColumn {

                item {
                    Text(text = "Active")
                }
                items(20) {
                    ShimmerListItem(
                        isLoading = isLoading,
                    contentAfterLoading = {
                        Row(modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)) {
                            Icon(imageVector = Icons.Default.Home, contentDescription = null, modifier = Modifier.size(100.dp))
                            Spacer(modifier = Modifier.width(16.dp))
                            Text(text = "this is a text to be shownnnnn :O ")
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp))
                }
                item { Text(text = "Past") }
                items(pastTickets) { item ->
                    HeheRow(item = item)
                }
            }
        }
    }

@Composable
fun ShimmerListItem(
    isLoading: Boolean,
    contentAfterLoading: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    if(isLoading) {
        Row(modifier = modifier) {
            Box(modifier = Modifier
                .size(100.dp)
                .shimmerEffect())
            Spacer(modifier = Modifier.width(16.dp))
            Column(Modifier.weight(1f)) {
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp)
                    .shimmerEffect())
                Spacer(modifier = Modifier.height(16.dp))
                Box(modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .height(20.dp)
                    .shimmerEffect())
            }
            
        }
    } else {
        contentAfterLoading()
    }
}

fun Modifier.shimmerEffect() : Modifier = composed { 
    var size by remember {
        mutableStateOf(IntSize.Zero)
    }
    val transition = rememberInfiniteTransition()
    val startOffsetX by transition.animateFloat(initialValue = -2 * size.width.toFloat(),
        targetValue = 2 * size.width.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(1000)
        ))

    background(
        brush = Brush.linearGradient(
            colors = listOf(
                Color(0xffb8b5b5),
                Color(0xff8f8b8b),
                Color(0xffb8b5b5),
            ),
            start = Offset(startOffsetX, 0f),
            end = Offset(startOffsetX + size.width.toFloat(), size.height.toFloat())
        )
    ).onGloballyPositioned { 
        size = it.size
    }
}

@Composable
fun HeheRow(item: Hehe) {
    Card(elevation = 4.dp, modifier = Modifier
        .padding(8.dp)

    ) {
        Column (modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(Color.White)
            .padding(8.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.Start){
            Text(text = item.outwardStatus, fontWeight = FontWeight.Bold,
                color = Color.Black,)
            Text(text = item.returnStatus, fontWeight = FontWeight.Bold,
                color = Color.Black,)

        }

    }
}

data class Hehe(
    val id: Int,
    val outwardStatus: String,
    val returnStatus: String
)