package com.example.dayscheduler

import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem( var title:String, var icon:Int, var screen_route:String){

    object Current : BottomNavItem("current", R.drawable.ic_baseline_home_24,"current")
    object AllSchedules : BottomNavItem("Items", R.drawable.ic_baseline_format_list_bulleted_24,"items")
    object CreateSchedule: BottomNavItem("Add",R.drawable.ic_baseline_add_24,"add")
    object Settings: BottomNavItem("Settings",R.drawable.ic_baseline_settings_24,"Settings")
}

sealed class NavItem(val title: String, val screen_route: String) {
    object CreateTask: NavItem("create task","create_tasks")
    object ConfirmScheduleView: NavItem("confirm schedule", "confirm_schedule")
}
