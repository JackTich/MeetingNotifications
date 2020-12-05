package com.jacktich.meetingnotifications.view.base.presenter

import com.jacktich.meetingnotifications.view.base.interactor.MVPInteractor
import com.jacktich.meetingnotifications.view.base.view.MVPView
import io.reactivex.rxjava3.disposables.CompositeDisposable

abstract class BasePresenter<V: MVPView, I: MVPInteractor>(
    protected var interactor: I?,
    protected val compositeDisposable: CompositeDisposable
): MVPPresenter<V, I> {

    private var view: V? = null

    override fun onAttach(view: V?) {
        this.view = view
    }

    override fun onDetach() {
        compositeDisposable.dispose()
        view = null
        interactor = null
    }

    override fun getView(): V? = view
}