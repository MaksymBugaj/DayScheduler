package com.example.dayscheduler.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.dayscheduler.data.db.converter.ZonedDateTimeConverter
import com.example.dayscheduler.data.db.dao.ScheduleDao
import com.example.dayscheduler.data.db.dao.ScheduleDateDao
import com.example.dayscheduler.data.db.dao.TaskDao
import com.example.dayscheduler.data.db.dao.TaskScheduleDao
import com.example.dayscheduler.data.db.entity.task.TaskEntity
import com.example.dayscheduler.data.db.entity.schedule.ScheduleDateEntity
import com.example.dayscheduler.data.db.entity.schedule.ScheduleEntity
import com.example.dayscheduler.data.db.entity.task.TaskScheduleEntity

@Database(
    entities = [
        TaskEntity::class,
        ScheduleEntity::class,
        ScheduleDateEntity::class,
        TaskScheduleEntity::class
    ],
    version = 1
)
@TypeConverters(
    ZonedDateTimeConverter::class
)
abstract class Database : RoomDatabase(){
    abstract fun taskDao(): TaskDao
    abstract fun taskScheduleDao(): TaskScheduleDao
    abstract fun scheduleDao(): ScheduleDao
    abstract fun scheduleDateDao(): ScheduleDateDao
}