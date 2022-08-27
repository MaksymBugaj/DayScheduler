package com.example.dayscheduler.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.dayscheduler.BottomNavItem
import com.example.dayscheduler.NavItem
import com.example.dayscheduler.ui.schedule.create.CreateScheduleView
import com.example.dayscheduler.ui.schedule.current.CurrentScheduleView
import com.example.dayscheduler.ui.schedule.list.AllSchedulesView
import com.example.dayscheduler.ui.settings.SettingsView
import com.example.dayscheduler.ui.task.create.CreateTaskView
import com.example.dayscheduler.ui.task.create.CreateTaskViewModel

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = BottomNavItem.Current.screen_route) {
        composable(route = BottomNavItem.Current.screen_route) {
            CurrentScheduleView()
        }
        composable(route = BottomNavItem.AllSchedules.screen_route) {
            AllSchedulesView()
        }
        composable(route = BottomNavItem.Settings.screen_route) {
            SettingsView()
        }
        composable(route = BottomNavItem.CreateSchedule.screen_route) {
            CreateScheduleView(onCreateClick = {
                navController.navigate(NavItem.CreateTask.screen_route)
            })
        }
        composable(route = NavItem.CreateTask.screen_route){
            val viewModel = hiltViewModel<CreateTaskViewModel>()
            CreateTaskView(viewModel)
        }
    }
}