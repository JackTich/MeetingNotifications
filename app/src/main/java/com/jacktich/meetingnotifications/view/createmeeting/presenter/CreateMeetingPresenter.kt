package com.jacktich.meetingnotifications.view.createmeeting.presenter

import com.jacktich.meetingnotifications.data.database.MeetingEntity
import com.jacktich.meetingnotifications.view.base.presenter.BasePresenter
import com.jacktich.meetingnotifications.view.createmeeting.interactor.CreateMeetingMVPInteractor
import com.jacktich.meetingnotifications.view.createmeeting.view.CreateMeetingMVPView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class CreateMeetingPresenter<V : CreateMeetingMVPView, I : CreateMeetingMVPInteractor> @Inject constructor(
    interactor: I,
    compositeDisposable: CompositeDisposable
) : CreateMeetingMVPPresenter<V, I>, BasePresenter<V, I>(interactor, compositeDisposable) {
    override fun insertMeeting(meeting: MeetingEntity) {
        compositeDisposable.add(
            interactor!!.insertMeeting(meeting)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                    {
                        getView()?.returnToListSuccess()
                    },
                    {
                        getView()?.showDBMessage()
                    }
                )
        )
    }
}