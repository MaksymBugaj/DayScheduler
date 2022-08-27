package com.example.dayscheduler.di

import android.content.Context
import androidx.room.Room
import com.example.dayscheduler.data.db.Database
import com.example.dayscheduler.data.db.dao.ScheduleDao
import com.example.dayscheduler.data.db.dao.ScheduleDateDao
import com.example.dayscheduler.data.db.dao.TaskDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): Database =
        Room.databaseBuilder(
            context,
            Database::class.java,
            "db"
        )
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun provideTaskDao(database: Database): TaskDao = database.taskDao()

    @Singleton
    @Provides
    fun provideScheduleDao(database: Database): ScheduleDao = database.scheduleDao()

    @Singleton
    @Provides
    fun provideScheduleDateDao(database: Database): ScheduleDateDao = database.scheduleDateDao()

}