package com.jacktich.meetingnotifications.view.meetingslist.interactor

import javax.inject.Inject

class MeetingsListInteractor @Inject constructor(val meetingDao: com.jacktich.meetingnotifications.data.database.MeetingDao): MeetingsListMVPInteractor {

    override fun getMeetingsList() = meetingDao.getMeetingList()

}