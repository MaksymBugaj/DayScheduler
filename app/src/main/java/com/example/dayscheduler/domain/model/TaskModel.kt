package com.example.dayscheduler.domain.model

import com.example.dayscheduler.data.db.entity.task.TaskEntity
import com.example.dayscheduler.ui.schedule.create.TaskItem

data class TaskModel(
    val id: Int,
    val name: String,
    val additionalInfo: String?
) {
    constructor(taskItem: TaskItem) : this (
        id = taskItem.id,
        name = taskItem.name,
        additionalInfo = taskItem.additionalInfo
    )


}
fun TaskModel.toTaskEntity(taskModel: TaskModel) : TaskEntity =
    TaskEntity(taskModel.id, taskModel.name, taskModel.additionalInfo)
