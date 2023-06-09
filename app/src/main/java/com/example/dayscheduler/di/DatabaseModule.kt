package com.example.dayscheduler.di

import android.content.Context
import androidx.room.Room
import com.example.dayscheduler.data.db.Database
import com.example.dayscheduler.data.db.dao.ScheduleDao
import com.example.dayscheduler.data.db.dao.ScheduleDateDao
import com.example.dayscheduler.data.db.dao.TaskDao
import com.example.dayscheduler.data.db.dao.TaskScheduleDao
import com.example.dayscheduler.data.db.migrations.RoomMigration3To4
import com.example.dayscheduler.data.db.migrations.RoomMigration4To5
import com.example.dayscheduler.data.db.migrations.RoomMigration5To6
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
        ).addMigrations(
            RoomMigration3To4(),
            RoomMigration4To5(),
            RoomMigration5To6()
        )
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun provideTaskDao(database: Database): TaskDao = database.taskDao()

    @Singleton
    @Provides
    fun provideTaskScheduleDao(database: Database): TaskScheduleDao = database.taskScheduleDao()

    @Singleton
    @Provides
    fun provideScheduleDao(database: Database): ScheduleDao = database.scheduleDao()

    @Singleton
    @Provides
    fun provideScheduleDateDao(database: Database): ScheduleDateDao = database.scheduleDateDao()

}