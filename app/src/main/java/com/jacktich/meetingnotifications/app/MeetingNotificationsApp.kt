package com.jacktich.meetingnotifications.app

import android.app.Activity
import android.app.Application
import androidx.room.Room
import com.jacktich.meetingnotifications.data.database.MeetingDatabase
import com.jacktich.meetingnotifications.di.components.DaggerAppComponents
import com.jacktich.meetingnotifications.utils.receivers.NotificationBroadcastReceiver
import com.jakewharton.threetenabp.AndroidThreeTen
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class MeetingNotificationsApp: Application(), HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
        DaggerAppComponents.builder()
            .application(this)
            .build()
            .inject(this)
    }

}