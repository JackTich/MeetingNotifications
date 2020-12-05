package com.jacktich.meetingnotifications.data.api.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class GetRandomUserList(
    @Expose
    @SerializedName("results")
    val results: List<DataUser>?,
    @Expose
    @SerializedName("error")
    val error: String?
)
data class DataUser(
    @Expose
    @SerializedName("name")
    val name: UserName,
    @Expose
    @SerializedName("email")
    val email: String,
    @Expose
    @SerializedName("picture")
    val picture: UserPicture
)
data class UserName(
    @Expose
    @SerializedName("title")
    val title: String,
    @Expose
    @SerializedName("first")
    val first: String,
    @Expose
    @SerializedName("last")
    val last: String
)
data class UserPicture(
    @Expose
    @SerializedName("medium")
    val medium: String
)