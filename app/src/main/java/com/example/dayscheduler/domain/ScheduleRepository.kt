package com.example.dayscheduler.domain

import com.example.dayscheduler.data.db.dao.ScheduleDao
import com.example.dayscheduler.data.db.dao.ScheduleDateDao
import com.example.dayscheduler.data.db.entity.TaskEntity
import com.example.dayscheduler.data.db.entity.schedule.ScheduleDateEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ScheduleRepository @Inject constructor(
    private val scheduleDao: ScheduleDao,
    private val scheduleDateDao: ScheduleDateDao
) {
    suspend fun saveScheduleDate(scheduleDate: ScheduleDateEntity) {
        withContext(Dispatchers.IO) {
            scheduleDateDao.insert(scheduleDate)
        }
    }

}