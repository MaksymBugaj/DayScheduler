package com.example.dayscheduler.data.db.entity.task

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.dayscheduler.data.db.entity.schedule.ScheduleEntity
/**
 * This entity is to store whaaat?
 */
@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val additionalInfo: String?
)
