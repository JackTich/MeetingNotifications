package com.jacktich.meetingnotifications.view.base.presenter

import com.jacktich.meetingnotifications.view.base.interactor.MVPInteractor
import com.jacktich.meetingnotifications.view.base.view.MVPView

interface MVPPresenter<V: MVPView, I: MVPInteractor> {

    fun onAttach(view: V?)

    fun onDetach()

    fun getView(): V?

}