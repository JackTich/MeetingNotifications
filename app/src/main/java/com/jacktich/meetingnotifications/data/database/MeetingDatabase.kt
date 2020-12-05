package com.jacktich.meetingnotifications.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MeetingEntity::class], version = 1, exportSchema = true)
abstract class MeetingDatabase: RoomDatabase() {

    companion object {
        @Volatile
        private var instance: MeetingDatabase? = null
        fun getInstance(context: Context): MeetingDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): MeetingDatabase {
            return Room.databaseBuilder(context, MeetingDatabase::class.java, "meetingdatabase")
                .build()
        }
    }

    abstract fun meetingDao(): MeetingDao
}