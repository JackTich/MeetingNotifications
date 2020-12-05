package com.jacktich.meetingnotifications.view.chooseclient.presenter

import com.jacktich.meetingnotifications.view.base.presenter.MVPPresenter
import com.jacktich.meetingnotifications.view.chooseclient.interactor.ChooseClientMVPInteractor
import com.jacktich.meetingnotifications.view.chooseclient.view.ChooseClientMVPView

interface ChooseClientMVPPresenter<V:ChooseClientMVPView, I: ChooseClientMVPInteractor >: MVPPresenter<V, I> {
    fun getRandomUserList()
}