package com.jacktich.meetingnotifications.view.createmeeting

import com.jacktich.meetingnotifications.data.database.MeetingDatabase
import com.jacktich.meetingnotifications.view.createmeeting.interactor.CreateMeetingInteractor
import com.jacktich.meetingnotifications.view.createmeeting.interactor.CreateMeetingMVPInteractor
import com.jacktich.meetingnotifications.view.createmeeting.presenter.CreateMeetingMVPPresenter
import com.jacktich.meetingnotifications.view.createmeeting.presenter.CreateMeetingPresenter
import com.jacktich.meetingnotifications.view.createmeeting.view.CreateMeetingMVPView
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CreateMeetingModule {

    @Provides
    fun provideCreateMeetingInteractor(interactor: CreateMeetingInteractor): CreateMeetingMVPInteractor =
        interactor

    @Provides
    fun provideCreateMeetingPresenter(presenter: CreateMeetingPresenter<CreateMeetingMVPView, CreateMeetingMVPInteractor>): CreateMeetingMVPPresenter<CreateMeetingMVPView, CreateMeetingMVPInteractor> =
        presenter

}