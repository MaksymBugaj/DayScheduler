package com.example.dayscheduler.data.db.entity

import androidx.room.Embedded
import androidx.room.Relation
import com.example.dayscheduler.data.db.entity.schedule.ScheduleDateEntity
import com.example.dayscheduler.data.db.entity.schedule.ScheduleEntity
import com.example.dayscheduler.data.db.entity.task.TaskEntity
import com.example.dayscheduler.data.db.entity.task.TaskScheduleEntity
/**
 * This entity is to store whaaat?
 */
data class ScheduleFull(
    @Embedded
    val schedule: ScheduleEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "scheduleId",
        entity = ScheduleDateEntity::class
    )
    val scheduleDates: List<ScheduleDateEntity>,
    @Relation(
        parentColumn = "id",
        entityColumn = "scheduleId",
        entity = TaskScheduleEntity::class
    )
    val tasks: List<TaskScheduleEntity>

)
