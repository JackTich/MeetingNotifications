package com.jacktich.meetingnotifications.view.meetingslist.view

import com.jacktich.meetingnotifications.data.database.MeetingEntity
import com.jacktich.meetingnotifications.view.base.view.MVPView

interface MeetingsListMVPView: MVPView {
    fun initMeetingsAdapter(listMeetings: List<MeetingEntity>)
    fun createNotifications(listMeetings: List<MeetingEntity>)
    fun showDBError()
}