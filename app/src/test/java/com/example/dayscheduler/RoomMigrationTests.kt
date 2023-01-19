package com.example.dayscheduler

import androidx.room.Room
import androidx.room.testing.MigrationTestHelper
import androidx.sqlite.db.framework.FrameworkSQLiteOpenHelperFactory
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import com.example.dayscheduler.data.db.Database
import com.example.dayscheduler.data.db.migrations.RoomMigration3To4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4ClassRunner::class)
class RoomMigrationTests {

    companion object {
        private const val TEST_DB = "migration-tests"
    }

    @get:Rule
    val helper: MigrationTestHelper = MigrationTestHelper(
        InstrumentationRegistry.getInstrumentation(),
        Database::class.java.canonicalName,
        FrameworkSQLiteOpenHelperFactory()
    )

    @Test
    @Throws(IOException::class)
    fun migrate(){
        //helper.createDatabase(TEST_DB, 1).apply { close() }
        Room.databaseBuilder(InstrumentationRegistry.getInstrumentation().targetContext, Database::class.java, TEST_DB)
            .addMigrations(
                RoomMigration3To4()
            ).build()
            .apply {
                openHelper.writableDatabase
                close()
            }
    }
}