package com.example.dayscheduler.domain

import com.example.dayscheduler.data.db.dao.TaskDao
import com.example.dayscheduler.data.db.entity.task.TaskEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TaskRepository @Inject constructor(
    private val taskDao: TaskDao,
) {

    suspend fun saveTask(task: TaskEntity) {
        withContext(Dispatchers.IO) {
            taskDao.insert(task)
        }
    }

    suspend fun getAllTasks(): Flow<List<TaskEntity>> {
        return withContext(Dispatchers.IO) {
            taskDao.getAllTasks()
        }
    }
}