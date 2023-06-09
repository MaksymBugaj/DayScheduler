package com.example.dayscheduler


sealed class BottomNavItem(val title:String, val icon:Int, val screen_route:String){

    object Current : BottomNavItem("Current", R.drawable.ic_baseline_format_list_bulleted_24,NavRoute.current)
    object CompletedTasks : BottomNavItem("Completed", R.drawable.completed,NavRoute.completed)
    object CreateSchedule: BottomNavItem("Create",R.drawable.ic_baseline_add_24,NavRoute.create)
    object Settings: BottomNavItem("Settings",R.drawable.ic_baseline_settings_24,"Settings")
}

sealed class NavItem(val title: String, val screen_route: String) {
    object CreateTask: NavItem("create task",NavRoute.createTask)
    object ConfirmScheduleView: NavItem("confirm schedule", NavRoute.confirmSchedule)
}

object NavRoute {
    const val current = "current"
    const val completed = "completed"
    const val create = "create"
    const val createTask = "create_task"
    const val confirmSchedule = "confirm"
}