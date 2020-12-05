package com.jacktich.meetingnotifications.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "meeting")
class MeetingEntity (
    @PrimaryKey(autoGenerate = true)
     val id: Long = 0,
    @ColumnInfo(name = "title")
     val title: String,
    @ColumnInfo(name = "date")
     val date: String,
    @ColumnInfo(name = "userName")
     val userName: String,
    @ColumnInfo(name = "email")
     val email: String,
    @ColumnInfo(name = "img")
     val img: String,
    @ColumnInfo(name = "time")
     val time: String?
)
