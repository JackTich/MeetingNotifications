package com.jacktich.meetingnotifications.view.chooseclient.presenter

import com.jacktich.meetingnotifications.view.base.presenter.BasePresenter
import com.jacktich.meetingnotifications.view.chooseclient.interactor.ChooseClientMVPInteractor
import com.jacktich.meetingnotifications.view.chooseclient.view.ChooseClientMVPView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.internal.http2.Http2
import retrofit2.HttpException
import retrofit2.http.HTTP
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject
import javax.net.ssl.HttpsURLConnection

class ChooseClientPresenter<V : ChooseClientMVPView, I : ChooseClientMVPInteractor>
@Inject constructor(
    interactor: I,
    compositeDisposable: CompositeDisposable
) : BasePresenter<V, I>(interactor, compositeDisposable), ChooseClientMVPPresenter<V, I> {

    private lateinit var disposeRandomUser: Disposable

    override fun getRandomUserList() {
        interactor?.let {
            if (this::disposeRandomUser.isInitialized && !disposeRandomUser.isDisposed){
                disposeRandomUser.dispose()
            }
            disposeRandomUser = it.doServerGetRandomUserList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                    { response ->
                        if (response.error == null) {
                            getView()!!.initRandomUserAdapter(response.results!!)
                        } else {
                            getView()?.showServerMessage(response.error)
                        }
                    }, { error ->
                        when (error){
                            is HttpException ->  getView()?.showErrorCode(error.code())
                            is UnknownHostException -> getView()?.showNoConnectionError()
                            else -> getView()?.showError()
                        }
                        error.printStackTrace()
                    }
                )
            compositeDisposable.add(
                disposeRandomUser
            )
        }
    }

}