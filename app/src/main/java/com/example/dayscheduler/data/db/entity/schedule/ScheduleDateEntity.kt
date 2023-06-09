package com.example.dayscheduler.data.db.entity.schedule

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import java.time.ZonedDateTime
/**
 * This entity is to store schedule dates, for example repeat only on friday,
 * so nowadays it should keep only int of a day
 * (keep in mind that days are stored from 1, not 0.
 */
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
    val day: Int,
    val date: ZonedDateTime
)
