package com.example.dayscheduler.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.dayscheduler.data.db.entity.task.TaskEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao : BaseDao<TaskEntity> {

    @Query("select * from tasks")
    fun getAllTasks(): Flow<List<TaskEntity>>

    @Query("select * from tasks order by tasks.id desc limit 1")
    suspend fun getLastTask(): TaskEntity

    @Query("select * from tasks where tasks.id = :id")
    suspend fun getTaskWithId(id: Int): TaskEntity

    @Query("select * from tasks where id in (:ids)")
    suspend fun getAllTasksWithId(ids: List<Int>): List<TaskEntity>
}