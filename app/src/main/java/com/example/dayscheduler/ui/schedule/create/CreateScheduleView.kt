package com.example.dayscheduler.ui.schedule.create

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
fun CreateScheduleView (
    viewModel: CreateScheduleViewModel,
    onCreateTaskClick: () -> Unit
){
    val tasks by viewModel.tasks.observeAsState(initial = emptyList())


    Column(modifier = Modifier
        .fillMaxSize()
        .padding(8.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        if(tasks.isEmpty()){
            ShowCreateTaskView(onCreateTaskClick)
        } else {
            CreateSchedule()
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
        Text(text = "There are no tasks, go and create one!", modifier = Modifier.padding(8.dp).clickable {
            onCreateTaskClick()
        }, textAlign = TextAlign.Center)
    }
}

@Composable
fun CreateSchedule() {

}