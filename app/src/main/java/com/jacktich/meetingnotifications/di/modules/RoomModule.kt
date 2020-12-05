package com.jacktich.meetingnotifications.di.modules

import android.app.Application
import androidx.room.Room
import com.jacktich.meetingnotifications.data.database.MeetingDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {

    @Singleton
    @Provides
    fun provideRoomDB(application: Application): MeetingDatabase = MeetingDatabase.getInstance(application)

    @Singleton
    @Provides
    fun provideMeetingsDao(database: MeetingDatabase) = database.meetingDao()

}