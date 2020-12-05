package com.jacktich.meetingnotifications.view.createmeeting.models

import android.os.Parcel
import android.os.Parcelable

data class CreateUserFieldsModel(
    var title: String? = null,
    var date: String? = null,
    var time: String? = null
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(date)
        parcel.writeString(time)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CreateUserFieldsModel> {
        override fun createFromParcel(parcel: Parcel): CreateUserFieldsModel {
            return CreateUserFieldsModel(parcel)
        }

        override fun newArray(size: Int): Array<CreateUserFieldsModel?> {
            return arrayOfNulls(size)
        }
    }
}