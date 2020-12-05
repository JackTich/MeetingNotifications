package com.jacktich.meetingnotifications.view.meetingslist.presenter

import com.jacktich.meetingnotifications.view.base.presenter.BasePresenter
import com.jacktich.meetingnotifications.view.meetingslist.interactor.MeetingsListMVPInteractor
import com.jacktich.meetingnotifications.view.meetingslist.view.MeetingsListMVPView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class MeetingsListPresenter<V : MeetingsListMVPView, I : MeetingsListMVPInteractor> @Inject constructor(
    interactor: I,
    compositeDisposable: CompositeDisposable
) : BasePresenter<V, I>(interactor, compositeDisposable), MeetingsListMVPPresenter<V, I> {

    override fun getMeetingsList() {
        compositeDisposable.add(
            interactor!!.getMeetingsList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                    {
                        getView()?.initMeetingsAdapter(it)
                        getView()?.createNotifications(it)
                    },
                    {
                        getView()?.showDBError()
                    }
                )
        )
    }

}