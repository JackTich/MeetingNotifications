package com.jacktich.meetingnotifications.view.chooseclient

import com.jacktich.meetingnotifications.view.chooseclient.view.ChooseClientFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ChooseClientProvider {
    @ContributesAndroidInjector(modules = [ChooseClientModule::class])
    abstract fun provideChooseClientFragmentFactory(): ChooseClientFragment
}
