package com.jacktich.meetingnotifications.view.createmeeting.interactor

import com.jacktich.meetingnotifications.data.database.MeetingEntity
import javax.inject.Inject

class CreateMeetingInteractor @Inject constructor(val meetingDao: com.jacktich.meetingnotifications.data.database.MeetingDao): CreateMeetingMVPInteractor {
    override fun insertMeeting(meeting: MeetingEntity) = meetingDao.insertMeeting(meeting)
}