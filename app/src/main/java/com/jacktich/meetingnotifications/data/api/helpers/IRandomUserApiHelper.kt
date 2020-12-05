package com.jacktich.meetingnotifications.data.api.helpers

import com.jacktich.meetingnotifications.data.api.responses.GetRandomUserList
import com.jacktich.meetingnotifications.utils.constants.AppConstants
import com.jacktich.meetingnotifications.utils.constants.Urls
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface IRandomUserApiHelper {
    @GET(Urls.URL_USER_LIST)
    fun performServerGetRandomUser(
        @Query("results") resultsCount: Int = AppConstants.LIMIT_GENERATED_USER
    ): Observable<GetRandomUserList>
}