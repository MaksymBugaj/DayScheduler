package com.example.dayscheduler.di

import com.example.dayscheduler.domain.usecase.CreateScheduleUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface UseCaseDependencies {

    fun exposeCreateScheduleUseCase(): CreateScheduleUseCase
}