package com.example.dayscheduler.ui.settings

import java.time.LocalDateTime

data class AlarmItem(
    val time: LocalDateTime,
    val message: String
)
