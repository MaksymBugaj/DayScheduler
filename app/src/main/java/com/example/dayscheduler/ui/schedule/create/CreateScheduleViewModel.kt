package com.example.dayscheduler.ui.schedule.create

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dayscheduler.data.db.entity.ScheduleFull
import com.example.dayscheduler.data.db.entity.task.TaskEntity
import com.example.dayscheduler.domain.ScheduleRepository
import com.example.dayscheduler.domain.TaskRepository
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

    private val _tasks = MutableLiveData<List<TaskEntity>>()
    val tasks : LiveData<List<TaskEntity>> = _tasks

    init {
        viewModelScope.launch {
            scheduleRepository.getAllSchedules().onEach {
                _schedules.postValue(it)
            }.launchIn(this)
            taskRepository.getAllTasks().onEach {
                _tasks.postValue(it)
            }.launchIn(this)
        }
    }
}