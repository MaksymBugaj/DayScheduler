package com.example.dayscheduler.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
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
    @Query(
        "select * from schedules s where s.finished = 0"
    )
    suspend fun getCurrentScheduleFull(): ScheduleFull?

    @Transaction
    @Query("select * from schedules s order by s.created desc limit 1")
    suspend fun getLastSchedule(): List<ScheduleFull>

    @Update
    fun markScheduleAsCompleted(schedule: ScheduleEntity)
}