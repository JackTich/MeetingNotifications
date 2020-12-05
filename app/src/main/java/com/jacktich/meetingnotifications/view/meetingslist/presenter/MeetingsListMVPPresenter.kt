package com.jacktich.meetingnotifications.view.meetingslist.presenter

import com.jacktich.meetingnotifications.view.base.presenter.MVPPresenter
import com.jacktich.meetingnotifications.view.meetingslist.interactor.MeetingsListMVPInteractor
import com.jacktich.meetingnotifications.view.meetingslist.view.MeetingsListMVPView

interface MeetingsListMVPPresenter<V: MeetingsListMVPView, I: MeetingsListMVPInteractor>: MVPPresenter<V, I> {
    fun getMeetingsList()
}