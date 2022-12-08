package com.example.dayscheduler.domain.repository

import com.example.dayscheduler.data.db.dao.ScheduleDao
import com.example.dayscheduler.data.db.dao.ScheduleDateDao
import com.example.dayscheduler.data.db.dao.TaskScheduleDao
import com.example.dayscheduler.data.db.entity.ScheduleFull
import com.example.dayscheduler.data.db.entity.schedule.ScheduleDateEntity
import com.example.dayscheduler.data.db.entity.schedule.ScheduleEntity
import com.example.dayscheduler.data.db.entity.task.TaskScheduleEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ScheduleRepository @Inject constructor(
    private val scheduleDao: ScheduleDao,
    private val scheduleDateDao: ScheduleDateDao,
    //to be moved
    private val taskScheduleDao: TaskScheduleDao
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

    suspend fun createSchedule(scheduleEntity: ScheduleEntity): Long {
        return withContext(Dispatchers.IO) {
            scheduleDao.insertReturnId(scheduleEntity)
        }
    }

    suspend fun saveTaskScheduleWithCorrespondingId(taskScheduleEntities: List<TaskScheduleEntity>) {
        withContext(Dispatchers.IO) {
            taskScheduleDao.insert(taskScheduleEntities)
        }
    }
}