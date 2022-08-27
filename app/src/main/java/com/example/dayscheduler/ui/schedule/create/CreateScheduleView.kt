package com.example.dayscheduler.ui.schedule.create

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
fun CreateScheduleView (
    onCreateClick: () -> Unit
){
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "trollloololo create scheudle", modifier = Modifier.clickable {
            onCreateClick()
        })
    }
}