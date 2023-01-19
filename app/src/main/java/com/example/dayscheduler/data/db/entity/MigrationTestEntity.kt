package com.example.dayscheduler.data.db.entity

import androidx.room.Entity

@Entity(
    tableName = "tests_tmp",
    primaryKeys = ["id"]
)
data class MigrationTestEntity(
    val id: Int,
    val title: String?
)
