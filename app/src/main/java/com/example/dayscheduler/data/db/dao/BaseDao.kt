package com.example.dayscheduler.data.db.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

interface BaseDao<T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg entity: T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entities: List<T>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertReturnId(entity: T): Long

    @Update
    fun update(entity: T)

    @Update
    fun update(vararg entity: T)

    @Update
    fun update(entities: List<T>)

    @Delete
    fun delete(entity: T)

    @Delete
    fun delete(vararg entity: T)

    @Delete
    fun delete(entities: List<T>)
}