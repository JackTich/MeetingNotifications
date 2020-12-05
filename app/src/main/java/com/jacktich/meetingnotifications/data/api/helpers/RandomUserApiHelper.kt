package com.jacktich.meetingnotifications.data.api.helpers

import com.jacktich.meetingnotifications.data.api.responses.GetRandomUserList
import io.reactivex.rxjava3.core.Observable
import retrofit2.Retrofit
import javax.inject.Inject

class RandomUserApiHelper @Inject constructor(val apiBuilder: Retrofit): IRandomUserApiHelper {
    override fun performServerGetRandomUser(resultsCount: Int): Observable<GetRandomUserList> =
        apiBuilder.create(IRandomUserApiHelper::class.java).performServerGetRandomUser()
}