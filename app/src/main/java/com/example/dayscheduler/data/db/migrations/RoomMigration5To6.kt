package com.example.dayscheduler.data.db.migrations

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

class RoomMigration5To6: Migration(5,6) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("alter table `schedules_dates` add column `date` text not null default '1' ")
    }
}