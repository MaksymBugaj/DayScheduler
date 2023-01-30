package com.example.dayscheduler.data.mapper

import com.example.dayscheduler.data.db.entity.task.TaskEntity
import com.example.dayscheduler.data.mapper.base.BaseDataMapper
import com.example.dayscheduler.domain.model.TaskModel
import javax.inject.Inject

class TaskMapper @Inject constructor() : BaseDataMapper<TaskEntity, TaskModel>{
    override fun mapToData(domainModel: TaskModel): TaskEntity =
        TaskEntity(
            id = domainModel.id,
            name = domainModel.name,
            additionalInfo = domainModel.additionalInfo
        )

    override fun mapFromData(dataModel: TaskEntity): TaskModel =
        TaskModel(
            id = dataModel.id,
            name = dataModel.name,
            additionalInfo = dataModel.additionalInfo,
            isActive = true
        )
}