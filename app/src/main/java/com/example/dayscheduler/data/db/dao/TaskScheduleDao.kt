package com.example.dayscheduler.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.dayscheduler.data.db.entity.task.TaskEntity
import com.example.dayscheduler.data.db.entity.task.TaskScheduleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskScheduleDao : BaseDao<TaskScheduleEntity> {

    @Query("select ts.taskId from task_schedules ts where ts.scheduleId= :scheduleId and ts.isActive = 0")
    suspend fun getAllFinishedTasksIds(scheduleId: Int): List<Int>

    @Query("delete from task_schedules where scheduleId != :scheduleId")
    suspend fun deleteAllFinishedTasksFromFinishedSchedules(scheduleId: Int)

}