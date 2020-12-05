package com.jacktich.meetingnotifications.view.meetingslist

import androidx.fragment.app.Fragment
import com.jacktich.meetingnotifications.view.meetingslist.view.MeetingsListFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
abstract class MeetingsListProvider {

    @ContributesAndroidInjector(modules = [MeetingListModule::class])
    abstract fun provideMeetingListFragmentFactory(): MeetingsListFragment

}