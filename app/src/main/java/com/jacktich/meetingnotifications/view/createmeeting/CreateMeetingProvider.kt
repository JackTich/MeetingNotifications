package com.jacktich.meetingnotifications.view.createmeeting

import com.jacktich.meetingnotifications.view.createmeeting.view.CreateMeetingFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class CreateMeetingProvider {
    @ContributesAndroidInjector(modules = [CreateMeetingModule::class])
    abstract fun provideCreateMeetingFragmentFactory(): CreateMeetingFragment
}
