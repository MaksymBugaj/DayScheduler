package com.example.dayscheduler.data.db.entity.task

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.dayscheduler.data.db.entity.schedule.ScheduleEntity
import com.example.dayscheduler.domain.model.TaskModel

/**
 * This entity is to store the task id connected to the schedule id
 */
@Entity(tableName = "task_schedules",
    foreignKeys = [
        ForeignKey(
            entity = ScheduleEntity::class,
            childColumns = ["scheduleId"],
            parentColumns = ["id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class TaskScheduleEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val scheduleId: Int
) {
    constructor(taskModel: TaskModel, scheduleId: Int) : this (
            id = taskModel.id, scheduleId = scheduleId
            )
}
