package com.example.dayscheduler.ui.schedule.current

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dayscheduler.data.db.entity.ScheduleFull
import com.example.dayscheduler.data.db.entity.task.TaskEntity
import com.example.dayscheduler.domain.repository.ScheduleRepository
import com.example.dayscheduler.util.livedata.SafeLiveData
import com.example.dayscheduler.util.livedata.SafeMutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrentScheduleViewModel @Inject constructor(
    private val scheduleRepository: ScheduleRepository
) : ViewModel() {

    private val _tasks = SafeMutableLiveData<List<TaskEntity>>()
    val tasks: SafeLiveData<List<TaskEntity>> get() = _tasks

    init {
        viewModelScope.launch {
            _tasks.postValue(scheduleRepository.getLastSchedule())
        }
    }
}