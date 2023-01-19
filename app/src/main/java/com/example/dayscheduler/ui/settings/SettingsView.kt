package com.example.dayscheduler.ui.settings

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import java.time.LocalDateTime

@Composable
fun SettingsView() {
    val scheduler = AndroidAlarmScheduler(LocalContext.current)
    var alarmItem: AlarmItem? = null
    var secondsText by remember {
        mutableStateOf("")
    }
    var message by remember {
        mutableStateOf("")
    }
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp), verticalArrangement = Arrangement.Center) {

        OutlinedTextField(
            value = secondsText,
            onValueChange = {
                secondsText = it
        },
        modifier = Modifier.fillMaxWidth(),
        placeholder = {
            Text(text = "Trigger alarm in seconds")
        },
        label = { Text(text = "trigger")})

        OutlinedTextField(
            value = message,
            onValueChange = {
                message = it
            },
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(text = "message")
            },
            label = { Text(text = "message")})
        
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Button(onClick = {
                alarmItem = AlarmItem(
                    time = LocalDateTime.now()
                        .plusSeconds(secondsText.toLong()),
                    message = message
                )
                alarmItem?.let(scheduler::schedule)
            }) {
                Text(text = "Schedule")
            }
            Button(onClick = {
                alarmItem?.let (scheduler::cancel)
            }) {
                Text(text = "Cancel")
            }

        }
    }
}