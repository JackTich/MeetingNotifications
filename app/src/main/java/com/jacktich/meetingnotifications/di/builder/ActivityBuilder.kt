package com.jacktich.meetingnotifications.di.builder

import com.jacktich.meetingnotifications.view.activitymeetings.MeetingsActivity
import com.jacktich.meetingnotifications.view.chooseclient.ChooseClientModule
import com.jacktich.meetingnotifications.view.chooseclient.ChooseClientProvider
import com.jacktich.meetingnotifications.view.createmeeting.CreateMeetingModule
import com.jacktich.meetingnotifications.view.createmeeting.CreateMeetingProvider
import com.jacktich.meetingnotifications.view.meetingslist.MeetingListModule
import com.jacktich.meetingnotifications.view.meetingslist.MeetingsListProvider
import dagger.Module
import dagger.android.ContributesAndroidInjector
import javax.inject.Singleton

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(
        modules = [
            CreateMeetingProvider::class,
            MeetingsListProvider::class,
            ChooseClientProvider::class
        ]
    )
    abstract fun bindMeetingsActivity(): MeetingsActivity

}