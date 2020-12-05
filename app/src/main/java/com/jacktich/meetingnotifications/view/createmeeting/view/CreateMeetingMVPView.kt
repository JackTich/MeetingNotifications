package com.jacktich.meetingnotifications.view.createmeeting.view

import com.jacktich.meetingnotifications.view.base.view.MVPView

interface CreateMeetingMVPView: MVPView {
    fun returnToListSuccess()
    fun showDBMessage()
}