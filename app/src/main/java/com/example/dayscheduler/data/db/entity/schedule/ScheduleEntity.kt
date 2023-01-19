package com.example.dayscheduler.data.db.entity.schedule

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.ZonedDateTime
/**
 * This entity is to store goal of a schedule, ex. daily
 */
@Entity(tableName = "schedules")
data class ScheduleEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val goal: String,
    val created: ZonedDateTime
)
