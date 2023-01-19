package com.example.dayscheduler.data.db.migrations

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

class RoomMigration3To4: Migration(3,4) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "create table if not exists `tests_tmp`(" +
                    "`id` integer not null primary key, " +
                    "`title` text)"
        )
    }
}