package com.example.dayscheduler.data.db.migrations

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

class RoomMigration4To5: Migration(4,5) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("alter table `task_schedules` add column `isActive` integer not null")
        database.execSQL("alter table `schedules` add column `finished` integer not null")
    }
}