package com.example.dayscheduler.domain.repository

import com.example.dayscheduler.data.db.dao.ScheduleDao
import com.example.dayscheduler.data.db.dao.ScheduleDateDao
import com.example.dayscheduler.data.db.dao.TaskDao
import com.example.dayscheduler.data.db.dao.TaskScheduleDao
import com.example.dayscheduler.data.db.entity.ScheduleFull
import com.example.dayscheduler.data.db.entity.schedule.ScheduleDateEntity
import com.example.dayscheduler.data.db.entity.schedule.ScheduleEntity
import com.example.dayscheduler.data.db.entity.task.TaskEntity
import com.example.dayscheduler.data.db.entity.task.TaskScheduleEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ScheduleRepository @Inject constructor(
    private val scheduleDao: ScheduleDao,
    private val scheduleDateDao: ScheduleDateDao,
    //to be moved
    private val taskScheduleDao: TaskScheduleDao,
    private val taskDao: TaskDao,
) {
    suspend fun saveScheduleDate(scheduleDate: ScheduleDateEntity) {
        withContext(Dispatchers.IO) {
            scheduleDateDao.insert(scheduleDate)
        }
    }

    suspend fun getAllSchedules(): Flow<List<ScheduleFull>>{
        return withContext(Dispatchers.IO) {
            scheduleDao.getScheduleFull()
        }
    }

    suspend fun getCurrentSchedule(): ScheduleFull? {
        return withContext(Dispatchers.IO) {
            return@withContext scheduleDao.getCurrentScheduleFull()
        }
    }

    suspend fun getLastScheduleTasks(): Pair<List<TaskScheduleEntity>,List<TaskEntity>> {
        return withContext(Dispatchers.IO) {
            return@withContext scheduleDao.getCurrentScheduleFull()?.let { schedule ->
                Pair(schedule.tasks,taskDao.getAllTasksWithId(schedule.tasks.filter { it.isActive }.map { it.taskId }))
            } ?: return@withContext Pair(emptyList(),emptyList())
        }
    }

    suspend fun createSchedule(scheduleEntity: ScheduleEntity): Long {
        return withContext(Dispatchers.IO) {
            scheduleDao.insertReturnId(scheduleEntity)
        }
    }

    suspend fun saveTaskScheduleWithCorrespondingId(taskScheduleEntities: List<TaskScheduleEntity>) {
        withContext(Dispatchers.IO) {
            for(item in taskScheduleEntities) taskScheduleDao.insert(item)
        }
    }

    suspend fun markTaskAsCompleted(taskScheduleEntity: List<TaskScheduleEntity>) {
        withContext(Dispatchers.IO) {
            taskScheduleDao.update(taskScheduleEntity)
        }
    }

    suspend fun getFinishedTasksFromLastSchedule(): List<TaskEntity>{
        getCurrentSchedule()?.let { scheduleFull ->
            val finishedIds = taskScheduleDao.getAllFinishedTasksIds(scheduleFull.schedule.id)
            if(finishedIds.isNotEmpty()) return taskDao.getAllTasksWithId(finishedIds)
            else return emptyList()
        } ?: return emptyList()
    }

    suspend fun markScheduleAsFinished() {
        withContext(Dispatchers.IO){
            getCurrentSchedule()?.let {
                scheduleDao.markScheduleAsCompleted(it.schedule.copy(finished = true))
            }
        }
    }

    suspend fun deleteAllTaskFromFinishedSchedules() {
        withContext(Dispatchers.IO){
            getCurrentSchedule()?.let {
                taskScheduleDao.deleteAllFinishedTasksFromFinishedSchedules(it.schedule.id)
            }
        }
    }
}