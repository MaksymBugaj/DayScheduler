package com.example.dayscheduler.data.db.dao

import androidx.room.Dao
import com.example.dayscheduler.data.db.entity.schedule.ScheduleDateEntity

@Dao
interface ScheduleDateDao : BaseDao<ScheduleDateEntity> {
}