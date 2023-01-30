package com.example.dayscheduler.ui.schedule.current

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dayscheduler.data.db.entity.ScheduleFull
import com.example.dayscheduler.data.db.entity.task.TaskEntity
import com.example.dayscheduler.data.db.entity.task.TaskScheduleEntity
import com.example.dayscheduler.domain.model.TaskModel
import com.example.dayscheduler.domain.repository.ScheduleRepository
import com.example.dayscheduler.ui.schedule.create.TaskItem
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

    private val _currentScheduleTasks = MutableLiveData<List<TaskScheduleEntity>>()
    val scheduleTasks : LiveData<List<TaskScheduleEntity>> = _currentScheduleTasks

    private val _selectedTasks = SafeMutableLiveData<List<TaskModel>>(emptyList())
    val selectedTasks : LiveData<List<TaskModel>> = _selectedTasks

    private val taskList = mutableListOf<TaskItem>()

    init {
        updateInfo()
    }

    //fixme to be deleted and put flow here
    private fun updateInfo() {
        viewModelScope.launch {
            val schedule = scheduleRepository.getLastScheduleTasks()
            _tasks.postValue(schedule.second)
            _currentScheduleTasks.postValue(schedule.first)
            _selectedTasks.value = emptyList()
        }
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

    fun markAsCompleted() {
        val completedTasksIds = taskList.map {
            it.id
        }
        val currentTasksToComplete = _currentScheduleTasks.value?.filter {
            completedTasksIds.contains(it.taskId)
        }
        currentTasksToComplete?.let {
            viewModelScope.launch {
                scheduleRepository.markTaskAsCompleted(it.map { it.copy(isActive = false) })
                updateInfo()
            }
        }
    }
}