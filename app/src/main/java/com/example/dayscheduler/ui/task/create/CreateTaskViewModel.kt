package com.example.dayscheduler.ui.task.create

import androidx.lifecycle.*
import com.example.dayscheduler.data.db.entity.task.TaskEntity
import com.example.dayscheduler.domain.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateTaskViewModel @Inject constructor(
    private val taskRepository: TaskRepository
) : ViewModel(){

    private val _savePossible = MutableLiveData(false)
    val savePossible: LiveData<Boolean> = _savePossible

    private val _newTaskName = MutableLiveData("")
    val newTaskName: LiveData<String> = _newTaskName

    fun setNewTaskName(value: String){
        _newTaskName.value = value
    }

    private val _newTaskAdditionalInfo = MutableLiveData("")
    val newTaskNAdditionalInfo: LiveData<String> = _newTaskAdditionalInfo

    fun setNewTaskAdditionalInfo(value: String){
        _newTaskAdditionalInfo.value = value
    }

    fun save(){
        viewModelScope.launch {
            taskRepository.saveTask(TaskEntity(id = 0, name = _newTaskName.value!!, additionalInfo = _newTaskAdditionalInfo.value))
            _savePossible.postValue(true)
        }
    }

    fun setSavePossible(value: Boolean) {
        _savePossible.value = value
    }
}