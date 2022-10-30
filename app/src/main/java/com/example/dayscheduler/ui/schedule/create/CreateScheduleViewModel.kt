package com.example.dayscheduler.ui.schedule.create

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dayscheduler.data.db.entity.ScheduleFull
import com.example.dayscheduler.data.db.entity.task.TaskEntity
import com.example.dayscheduler.domain.ScheduleRepository
import com.example.dayscheduler.domain.TaskRepository
import com.example.dayscheduler.domain.model.TaskModel
import com.example.dayscheduler.ui.util.TAG
import com.example.dayscheduler.util.livedata.EmptySingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateScheduleViewModel @Inject constructor(
    private val scheduleRepository: ScheduleRepository,
    private val taskRepository: TaskRepository
) : ViewModel(){

    private val _schedules = MutableLiveData<List<ScheduleFull>>()
    val schedules : LiveData<List<ScheduleFull>> = _schedules

    private val _tasks = MutableLiveData<List<TaskItem>>()
    val tasks : LiveData<List<TaskItem>> = _tasks

    private val taskList = mutableListOf<TaskItem>()

    private val _selectedTasks = MutableLiveData<List<TaskModel>>(emptyList())
    val selectedTasks :LiveData<List<TaskModel>> = _selectedTasks

    val createScheduleClicked = EmptySingleLiveEvent()

    private val days = mutableListOf<Int>()
    private val _daysForSchedule = MutableLiveData<List<Int>>(emptyList())
    val daysForSchedule: LiveData<List<Int>> = _daysForSchedule

    init {
        viewModelScope.launch {
            scheduleRepository.getAllSchedules().onEach {
                _schedules.postValue(it)
            }.launchIn(this)
            taskRepository.getAllTasks().onEach { item ->
                _tasks.postValue(item.map {
                    TaskItem(
                        id = it.id,
                        name = it.name,
                        additionalInfo = it.additionalInfo,
                        isSelected = mutableStateOf(false)
                    )
                })
            }.launchIn(this)
        }
    }

    fun daysChanged(day: Int){
        if(days.contains(day)) days.remove(day)
        else days.add(day)

        _daysForSchedule.value = days
    }

    fun addSelectedTask(taskItem: TaskItem){
        taskList.add(taskItem)
        _selectedTasks.value = taskList.map { TaskModel(it) }
    }

    fun removeSelectedTask(taskItem: TaskItem) {
        taskList.remove(taskItem)
        _selectedTasks.value = taskList.map {
            TaskModel(it)
        }
    }

    fun onCreateScheduleClicked() {
        createScheduleClicked.emitEvent()
    }
}