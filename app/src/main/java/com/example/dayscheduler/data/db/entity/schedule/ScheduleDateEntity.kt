package com.example.dayscheduler.data.db.entity.schedule

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import java.time.ZonedDateTime

@Entity(
    tableName = "schedules_dates",
    foreignKeys = [
        ForeignKey(
            entity = ScheduleEntity::class,
            childColumns = ["scheduleId"],
            parentColumns = ["id"],
            onDelete = CASCADE
        )
    ]
)
data class ScheduleDateEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val scheduleId: Int,
    val date: ZonedDateTime
)
