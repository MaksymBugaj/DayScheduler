package com.example.dayscheduler.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.dayscheduler.data.db.entity.task.TaskEntity
import com.example.dayscheduler.data.db.entity.task.TaskScheduleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskScheduleDao : BaseDao<TaskScheduleEntity> {
}