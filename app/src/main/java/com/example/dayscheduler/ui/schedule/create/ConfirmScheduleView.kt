package com.example.dayscheduler.ui.schedule.create

import android.app.TimePickerDialog
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.Modifier.Companion.any
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.dayscheduler.ui.util.TAG
import java.text.DateFormatSymbols
import java.time.DayOfWeek
import java.util.*

@Composable
fun ConfirmScheduleView(viewModel: CreateScheduleViewModel) {
    // Declaring and initializing a calendar
    val mCalendar = Calendar.getInstance()
    val hour = mCalendar[Calendar.HOUR_OF_DAY]
    val minute = mCalendar[Calendar.MINUTE]
    // Value for storing time as a string
    val mTime = remember { mutableStateOf("") }
    // Creating a TimePicker dialod
    val mTimePickerDialog = TimePickerDialog(
        LocalContext.current,
        { _, mHour : Int, mMinute: Int ->
            mTime.value = "$mHour:$mMinute"
        }, hour, minute, true
    )
    val days : Array<String> =
    DateFormatSymbols.getInstance(Locale.getDefault()).shortWeekdays
    val showDaysDialog = remember {
        mutableStateOf(false)
    }
    val vmDays by viewModel.daysForSchedule.observeAsState(initial = emptyList())
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
        .padding(8.dp)
    ) {
        //przypomnij o: -> date chooser
        Text(
            text = "Set reminder hour for this schedule",
            style = MaterialTheme.typography.body1,
            modifier = Modifier.clickable { mTimePickerDialog.show() }
        )
        Text(text = mTime.value, style = MaterialTheme.typography.body2)
        //powtarzalność: -> days chooser jako alert dialog
        Text(
            text = "repeat",
            style = MaterialTheme.typography.body1,
            modifier = Modifier.clickable { showDaysDialog.value = true }
        )
        if(showDaysDialog.value) {
            CreateScheduleViewFromTasks(
                viewModel = viewModel,
                onDismiss = {
                showDaysDialog.value = false
            }, onConfirm = {
                showDaysDialog.value = false
            })
        }

        if(vmDays.isNotEmpty()) {
            val commonDays = vmDays.intersect(days.mapIndexed { index, _ -> index }.toSet())
            Log.d(TAG.commonTag," common days: $commonDays")
            Log.d(TAG.commonTag," days map indexed: ${days.mapIndexed { index, s -> index }}")


            val testCommon = days.filterIndexed { index, s ->
                Log.d(TAG.commonTag," mapIndexed: $index is: $s")
                commonDays.contains(index)
            }

            Log.d(TAG.commonTag," testCommon: $testCommon")
            val textToShow = testCommon.joinToString(separator = ", ")
            Text(text = textToShow, style = MaterialTheme.typography.body2)
        }
        SetScheduleGoal(viewModel = viewModel)
        SetScheduleName(viewModel = viewModel)
        SaveButton(viewModel)

    }
}

@Composable
fun SetScheduleGoal(viewModel: CreateScheduleViewModel) {
    val scheduleGoal by viewModel.scheduleGoal.observeAsState("")
    OutlinedTextField(
        value = scheduleGoal,
        onValueChange = {
        viewModel.setScheduleGoal(it)
        }
    )
}

@Composable
fun SetScheduleName(viewModel: CreateScheduleViewModel) {
    val scheduleName by viewModel.scheduleName.observeAsState("")
    OutlinedTextField(
        value = scheduleName,
        onValueChange = {
            viewModel.setScheduleName(it)
        }
    )
}

@Composable
fun SaveButton(viewModel: CreateScheduleViewModel) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(12.dp)
            .background(Color.Cyan),
        onClick = { viewModel.save() }) {
        Text(text = "Confirm", style = MaterialTheme.typography.body2)
    }
}

@Composable
fun CreateScheduleViewFromTasks(
    viewModel: CreateScheduleViewModel,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {

    val weekDays: Array<String> =
        DateFormatSymbols.getInstance(Locale.getDefault()).weekdays

    val weekDaysTest = DayOfWeek.values()


    Log.d(TAG.commonTag,"$weekDays")

    val openDialog = remember { mutableStateOf(true)  }
    if(openDialog.value) {
        AlertDialog(
            onDismissRequest = {
                onDismiss()
                openDialog.value = false
            },
            text = {
                var iterator = 1
                Column {
                    for (item in weekDays){
                        if(item.isNotEmpty()) {
                            WeekDayCheckBox(
                                iterator = iterator,
                                onCheckedChange = {
                                    Log.d(TAG.commonTag, "OnCheckedChanged: $it")
                                    viewModel.daysChanged(it)
                                },
                                label = item
                            )
                            ++iterator
                        }
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        onConfirm()
                        openDialog.value = false
                    }) {
                    Text("Confirm")
                }
            },
            dismissButton = {
                Button(

                    onClick = {
                        onDismiss()
                        openDialog.value = false
                    }) {
                    Text("dismiss")
                }
            }
        )
    }
}

@Composable
fun WeekDayCheckBox(
    iterator: Int,
    onCheckedChange: ((Int) -> Unit),
    label: String
) {
    val checked = remember {
        mutableStateOf(false)
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clip(MaterialTheme.shapes.small)
            .clickable(
                indication = rememberRipple(color = MaterialTheme.colors.primary),
                interactionSource = remember { MutableInteractionSource() },
                onClick = {
                    checked.value = !checked.value
                    onCheckedChange(iterator)
                }
            )
            .requiredHeight(ButtonDefaults.MinHeight)
            .padding(4.dp)
    ) {
        Checkbox(
            checked = checked.value,
            onCheckedChange = {
                checked.value = !checked.value
                onCheckedChange(iterator)
            }
        )

        Spacer(Modifier.size(6.dp))

        Text(
            text = label,
        )
    }
}