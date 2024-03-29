package com.example.dayscheduler.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.dayscheduler.BottomNavItem
import com.example.dayscheduler.NavItem
import com.example.dayscheduler.ui.schedule.completed.CompletedTaskView
import com.example.dayscheduler.ui.schedule.completed.CompletedTaskViewModel
import com.example.dayscheduler.ui.schedule.create.CreateScheduleView
import com.example.dayscheduler.ui.schedule.create.CreateScheduleViewModel
import com.example.dayscheduler.ui.schedule.current.CurrentScheduleView
import com.example.dayscheduler.ui.schedule.current.CurrentScheduleViewModel
import com.example.dayscheduler.ui.task.create.CreateTaskView
import com.example.dayscheduler.ui.task.create.CreateTaskViewModel

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = BottomNavItem.Current.screen_route) {
        composable(route = BottomNavItem.Current.screen_route) {
            val viewModel: CurrentScheduleViewModel = hiltViewModel()
            CurrentScheduleView(
                viewModel = viewModel,
                onAddTasksClick = {
                    navController.navigate(BottomNavItem.CreateSchedule.screen_route)
                }
            )
        }
        composable(route = BottomNavItem.CompletedTasks.screen_route) {
            val viewModel: CompletedTaskViewModel = hiltViewModel()
            CompletedTaskView(viewModel)
        }
//        composable(route = BottomNavItem.Settings.screen_route) {
//            SettingsView()
//        }
        composable(route = BottomNavItem.CreateSchedule.screen_route) {
            val viewModel = hiltViewModel<CreateScheduleViewModel>()
            CreateScheduleView(
                onCreateTaskClick = {
                    navController.navigate(NavItem.CreateTask.screen_route)
                },
                viewModel = viewModel,
                tasksSaved = {
                    navController.navigate(BottomNavItem.Current.screen_route)
                })
        }
        composable(route = NavItem.CreateTask.screen_route){
            val viewModel = hiltViewModel<CreateTaskViewModel>()
            CreateTaskView(
                viewModel = viewModel,
                onSaveReady = {
                    navController.popBackStack(route = BottomNavItem.CreateSchedule.screen_route, inclusive = false)
                }
            )
        }
    }
}