package com.example.dayscheduler.data.db.dao

import androidx.room.Dao
import com.example.dayscheduler.data.db.entity.schedule.ScheduleDateEntity
import com.example.dayscheduler.data.db.entity.schedule.ScheduleEntity

@Dao
interface ScheduleDao : BaseDao<ScheduleEntity> {
}