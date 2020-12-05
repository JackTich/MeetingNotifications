package com.jacktich.meetingnotifications.view.createmeeting.presenter

import com.jacktich.meetingnotifications.data.database.MeetingEntity
import com.jacktich.meetingnotifications.view.base.presenter.MVPPresenter
import com.jacktich.meetingnotifications.view.createmeeting.interactor.CreateMeetingMVPInteractor
import com.jacktich.meetingnotifications.view.createmeeting.view.CreateMeetingMVPView

interface CreateMeetingMVPPresenter<V: CreateMeetingMVPView, I: CreateMeetingMVPInteractor>: MVPPresenter<V, I> {
    fun insertMeeting(meeting: MeetingEntity)
}