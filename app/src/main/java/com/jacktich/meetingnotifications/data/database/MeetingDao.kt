package com.jacktich.meetingnotifications.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.jacktich.meetingnotifications.data.database.MeetingEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe

@Dao
interface MeetingDao{

    @Query("SELECT * FROM meeting")
    fun getMeetingList(): Maybe<List<MeetingEntity>>

    @Insert
    fun insertMeeting(meetingEntity: MeetingEntity): Completable

}