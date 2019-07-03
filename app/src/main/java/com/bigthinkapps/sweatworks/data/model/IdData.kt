package com.bigthinkapps.sweatworks.data.model

import android.os.Parcel
import android.os.Parcelable
import com.bigthinkapps.sweatworks.utils.AppConstants

data class IdData(
    val name: String = AppConstants.EMPTY_STRING,
    val value: String = AppConstants.EMPTY_STRING
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: AppConstants.EMPTY_STRING,
        parcel.readString() ?: AppConstants.EMPTY_STRING
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(value)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<IdData> {
        override fun createFromParcel(parcel: Parcel): IdData {
            return IdData(parcel)
        }

        override fun newArray(size: Int): Array<IdData?> {
            return arrayOfNulls(size)
        }
    }

}