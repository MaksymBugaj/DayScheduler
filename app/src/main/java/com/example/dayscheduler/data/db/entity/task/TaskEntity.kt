package com.example.dayscheduler.data.db.entity.task

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.dayscheduler.data.db.entity.schedule.ScheduleEntity
import com.example.dayscheduler.domain.model.TaskModel

/**
 * This entity is to store whaaat?
 */
@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val additionalInfo: String?
){
    constructor(taskModel: TaskModel) : this (
        id = taskModel.id,
        name = taskModel.name,
        additionalInfo = taskModel.additionalInfo
    )

}
