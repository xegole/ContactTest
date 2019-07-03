package com.bigthinkapps.sweatworks.data.model

import android.os.Parcel
import android.os.Parcelable
import com.bigthinkapps.sweatworks.utils.AppConstants

data class PictureData(
    val large: String = AppConstants.EMPTY_STRING,
    val medium: String = AppConstants.EMPTY_STRING,
    val thumbnail: String = AppConstants.EMPTY_STRING
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: AppConstants.EMPTY_STRING,
        parcel.readString() ?: AppConstants.EMPTY_STRING,
        parcel.readString() ?: AppConstants.EMPTY_STRING
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(large)
        parcel.writeString(medium)
        parcel.writeString(thumbnail)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PictureData> {
        override fun createFromParcel(parcel: Parcel): PictureData {
            return PictureData(parcel)
        }

        override fun newArray(size: Int): Array<PictureData?> {
            return arrayOfNulls(size)
        }
    }

}