package com.bigthinkapps.sweatworks.data.model

import android.os.Parcel
import android.os.Parcelable
import com.bigthinkapps.sweatworks.utils.AppConstants

data class DobData(
    val date: String = AppConstants.EMPTY_STRING,
    val age: Int = AppConstants.INT_ZERO
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: AppConstants.EMPTY_STRING,
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(date)
        parcel.writeInt(age)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DobData> {
        override fun createFromParcel(parcel: Parcel): DobData {
            return DobData(parcel)
        }

        override fun newArray(size: Int): Array<DobData?> {
            return arrayOfNulls(size)
        }
    }

}