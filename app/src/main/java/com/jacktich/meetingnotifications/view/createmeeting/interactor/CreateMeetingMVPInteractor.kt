package com.jacktich.meetingnotifications.view.createmeeting.interactor

import com.jacktich.meetingnotifications.data.database.MeetingEntity
import com.jacktich.meetingnotifications.view.base.interactor.MVPInteractor
import io.reactivex.rxjava3.core.Completable

interface CreateMeetingMVPInteractor: MVPInteractor {
    fun insertMeeting(meeting: MeetingEntity): Completable
}