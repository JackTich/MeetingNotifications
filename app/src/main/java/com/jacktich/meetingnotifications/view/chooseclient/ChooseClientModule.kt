package com.jacktich.meetingnotifications.view.chooseclient

import com.jacktich.meetingnotifications.view.chooseclient.interactor.ChooseClientInteractor
import com.jacktich.meetingnotifications.view.chooseclient.interactor.ChooseClientMVPInteractor
import com.jacktich.meetingnotifications.view.chooseclient.presenter.ChooseClientMVPPresenter
import com.jacktich.meetingnotifications.view.chooseclient.presenter.ChooseClientPresenter
import com.jacktich.meetingnotifications.view.chooseclient.view.ChooseClientMVPView
import dagger.Module
import dagger.Provides

@Module
class ChooseClientModule {

    @Provides
    fun provideChooseClientInteractor(interactor: ChooseClientInteractor): ChooseClientMVPInteractor =
        interactor

    @Provides
    fun provideChooseClientPresenter(presenter: ChooseClientPresenter<ChooseClientMVPView, ChooseClientMVPInteractor>): ChooseClientMVPPresenter<ChooseClientMVPView, ChooseClientMVPInteractor> =
        presenter
}