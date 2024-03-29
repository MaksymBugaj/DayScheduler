package com.example.dayscheduler.domain.repository

import android.util.Log
import com.example.dayscheduler.data.db.dao.TaskDao
import com.example.dayscheduler.data.mapper.TaskMapper
import com.example.dayscheduler.domain.model.TaskModel
import com.example.dayscheduler.ui.util.TAG
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TaskRepository @Inject constructor(
    private val taskDao: TaskDao,
    private val taskMapper: TaskMapper
) {

    suspend fun saveTask(task: TaskModel) {
        withContext(Dispatchers.IO) {
            taskDao.insert(taskMapper.mapToData(task))
        }
    }

    suspend fun getAllTasksWithoutSchedule(ids: List<Int>) : Flow<List<TaskModel>> {
        return withContext(Dispatchers.IO) {
            taskDao.getAllTasksWithoutSchedule(ids).map { list ->
                Log.d(TAG.commonTag, "task repo flow: $list")
                list.map { taskEntity ->
                    taskMapper.mapFromData(taskEntity)
                }
            }
        }
    }

    suspend fun getAllTasks(): Flow<List<TaskModel>> {
        return withContext(Dispatchers.IO) {
            taskDao.getAllTasks().map { it.map { taskEntity -> taskMapper.mapFromData(taskEntity) } }
        }
    }
}