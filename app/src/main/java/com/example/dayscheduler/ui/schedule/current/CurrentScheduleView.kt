package com.example.dayscheduler.ui.schedule.current

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FabPosition
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.dayscheduler.ui.schedule.create.FloatingActionButtonAdd
import com.example.dayscheduler.ui.theme.schedulerColors

@Composable
fun CurrentScheduleView(
    onAddTasksClick: () -> Unit
) {

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(8.dp)) {
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
        Text(
            text = "no tasks for today, click and select them!",
            modifier = Modifier.padding(
                16.dp
            ).clickable {
                onAddTasksClick()
            },
            style = TextStyle(
                color = MaterialTheme.colors.primary,
                fontWeight = FontWeight.ExtraBold
            )
        )

    }
}

@Composable
fun TasksCounter() {

}