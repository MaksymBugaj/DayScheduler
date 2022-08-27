package com.example.dayscheduler.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.dayscheduler.data.db.converter.ZonedDateTimeConverter
import com.example.dayscheduler.data.db.dao.ScheduleDao
import com.example.dayscheduler.data.db.dao.ScheduleDateDao
import com.example.dayscheduler.data.db.dao.TaskDao
import com.example.dayscheduler.data.db.entity.TaskEntity
import com.example.dayscheduler.data.db.entity.schedule.ScheduleDateEntity
import com.example.dayscheduler.data.db.entity.schedule.ScheduleEntity

@Database(
    entities = [
        TaskEntity::class,
        ScheduleEntity::class,
        ScheduleDateEntity::class
    ],
    version = 1
)
@TypeConverters(
    ZonedDateTimeConverter::class
)
abstract class Database : RoomDatabase(){
    abstract fun taskDao(): TaskDao
    abstract fun scheduleDao(): ScheduleDao
    abstract fun scheduleDateDao(): ScheduleDateDao
}