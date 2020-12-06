package com.jacktich.meetingnotifications.view.chooseclient.interactor

import com.jacktich.meetingnotifications.data.api.helpers.RandomUserApiHelper
import com.jacktich.meetingnotifications.data.api.responses.GetRandomUserList
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class ChooseClientInteractor @Inject constructor(val randomUserApiHelper: RandomUserApiHelper): ChooseClientMVPInteractor {
    override fun doServerGetRandomUserList(): Observable<GetRandomUserList> =
        randomUserApiHelper.performServerGetRandomUser()
}