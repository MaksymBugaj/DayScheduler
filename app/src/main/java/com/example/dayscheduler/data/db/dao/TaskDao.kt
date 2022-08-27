package com.example.dayscheduler.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.dayscheduler.data.db.entity.TaskEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao : BaseDao<TaskEntity> {

    @Query("select * from tasks")
    fun getAllTasks(): Flow<List<TaskEntity>>
}