package com.jacktich.meetingnotifications.view.chooseclient.interactor

import com.jacktich.meetingnotifications.data.api.responses.GetRandomUserList
import com.jacktich.meetingnotifications.view.base.interactor.MVPInteractor
import io.reactivex.rxjava3.core.Observable

interface ChooseClientMVPInteractor: MVPInteractor {
    fun doServerGetRandomUserList(): Observable<GetRandomUserList>
}