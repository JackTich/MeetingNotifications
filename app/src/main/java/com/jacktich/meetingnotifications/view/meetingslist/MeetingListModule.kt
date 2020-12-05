package com.jacktich.meetingnotifications.view.meetingslist

import com.jacktich.meetingnotifications.data.database.MeetingDatabase
import com.jacktich.meetingnotifications.view.meetingslist.interactor.MeetingsListInteractor
import com.jacktich.meetingnotifications.view.meetingslist.interactor.MeetingsListMVPInteractor
import com.jacktich.meetingnotifications.view.meetingslist.presenter.MeetingsListMVPPresenter
import com.jacktich.meetingnotifications.view.meetingslist.presenter.MeetingsListPresenter
import com.jacktich.meetingnotifications.view.meetingslist.view.MeetingsListMVPView
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MeetingListModule {

    @Provides
    fun provideMeetingsListInteractor(interactor: MeetingsListInteractor): MeetingsListMVPInteractor =
        interactor

    @Provides
    fun provideMeetingsListPresenter(presenter: MeetingsListPresenter<MeetingsListMVPView, MeetingsListMVPInteractor>): MeetingsListMVPPresenter<MeetingsListMVPView, MeetingsListMVPInteractor> =
        presenter
}