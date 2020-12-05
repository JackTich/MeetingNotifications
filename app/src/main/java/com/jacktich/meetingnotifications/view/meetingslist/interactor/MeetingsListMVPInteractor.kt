package com.jacktich.meetingnotifications.view.meetingslist.interactor

import com.jacktich.meetingnotifications.data.database.MeetingEntity
import com.jacktich.meetingnotifications.view.base.interactor.MVPInteractor
import io.reactivex.rxjava3.core.Maybe

interface MeetingsListMVPInteractor: MVPInteractor {
    fun getMeetingsList(): Maybe<List<MeetingEntity>>
}