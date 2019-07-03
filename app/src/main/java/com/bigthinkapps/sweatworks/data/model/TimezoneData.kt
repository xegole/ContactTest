package com.bigthinkapps.sweatworks.data.model

import android.os.Parcel
import android.os.Parcelable
import com.bigthinkapps.sweatworks.utils.AppConstants

data class TimezoneData(
    val offset: String = AppConstants.EMPTY_STRING,
    val description: String = AppConstants.EMPTY_STRING
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: AppConstants.EMPTY_STRING,
        parcel.readString() ?: AppConstants.EMPTY_STRING
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(offset)
        parcel.writeString(description)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TimezoneData> {
        override fun createFromParcel(parcel: Parcel): TimezoneData {
            return TimezoneData(parcel)
        }

        override fun newArray(size: Int): Array<TimezoneData?> {
            return arrayOfNulls(size)
        }
    }

}