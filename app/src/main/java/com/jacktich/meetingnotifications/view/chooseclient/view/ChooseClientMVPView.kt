package com.jacktich.meetingnotifications.view.chooseclient.view

import com.jacktich.meetingnotifications.data.api.responses.DataUser
import com.jacktich.meetingnotifications.view.base.view.MVPView

interface ChooseClientMVPView: MVPView {
    fun showServerMessage(message: String)
    fun showNoConnectionError()
    fun showErrorCode(code: Int)
    fun initRandomUserAdapter(usersList: List<DataUser>)
    fun showError()
}