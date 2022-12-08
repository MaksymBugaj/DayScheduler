package com.example.dayscheduler.ui.task.create

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CreateTaskView(
    viewModel: CreateTaskViewModel,
    onSaveReady: () -> Unit
) {
    val taskName by viewModel.newTaskName.observeAsState("")
    val additionalInfo by viewModel.newTaskNAdditionalInfo.observeAsState(initial = "")
    val savePossible by viewModel.savePossible.observeAsState(initial = false)

    if(savePossible) {
        viewModel.setSavePossible(false)
        onSaveReady()
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Card(elevation = 2.dp, modifier = Modifier
            .padding(8.dp)
            .fillMaxSize()) {
            Column (horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center){
                OutlinedTextField(value = taskName, onValueChange = {
                    viewModel.setNewTaskName(it)
                }, label = {
                    Text(text = "name")
                })
                OutlinedTextField(value = additionalInfo, onValueChange = {
                    viewModel.setNewTaskAdditionalInfo(it)
                }, label = {
                    Text(text = "additional info")
                })
                Button(onClick = { viewModel.save() }) {
                    Text(text = "Save")
                }
            }

        }
    }
}