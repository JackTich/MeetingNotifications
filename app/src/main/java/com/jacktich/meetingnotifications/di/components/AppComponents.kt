package com.jacktich.meetingnotifications.di.components

import android.app.Application
import com.jacktich.meetingnotifications.app.MeetingNotificationsApp
import com.jacktich.meetingnotifications.di.builder.ActivityBuilder
import com.jacktich.meetingnotifications.di.modules.AppModule
import com.jacktich.meetingnotifications.di.modules.RoomModule
import com.jacktich.meetingnotifications.view.meetingslist.view.MeetingsListFragment
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, RoomModule::class, AppModule::class, ActivityBuilder::class])
interface AppComponents {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponents
    }

    fun inject(app: MeetingNotificationsApp)
}