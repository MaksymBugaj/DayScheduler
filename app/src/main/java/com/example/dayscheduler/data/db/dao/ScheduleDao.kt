package com.example.dayscheduler.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.dayscheduler.data.db.entity.ScheduleFull
import com.example.dayscheduler.data.db.entity.schedule.ScheduleDateEntity
import com.example.dayscheduler.data.db.entity.schedule.ScheduleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ScheduleDao : BaseDao<ScheduleEntity> {

    @Transaction
    @Query(
        "select * from schedules"
    )
    fun getScheduleFull(): Flow<List<ScheduleFull>>

    @Transaction
    @Query("select * from schedules s order by s.created desc limit 1")
    suspend fun getLastSchedule(): List<ScheduleFull>
}