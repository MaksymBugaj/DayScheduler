package com.example.dayscheduler.ui.schedule.completed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dayscheduler.data.db.entity.task.TaskEntity
import com.example.dayscheduler.domain.repository.ScheduleRepository
import com.example.dayscheduler.util.livedata.SafeLiveData
import com.example.dayscheduler.util.livedata.SafeMutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CompletedTaskViewModel @Inject constructor(
    private val scheduleRepository: ScheduleRepository
) : ViewModel() {

    private val _completedTasks = SafeMutableLiveData<List<TaskEntity>>()
    val completedTasks: SafeLiveData<List<TaskEntity>> get() = _completedTasks

    init {
        viewModelScope.launch {
            _completedTasks.postValue(scheduleRepository.getFinishedTasksFromLastSchedule())
        }
    }
}