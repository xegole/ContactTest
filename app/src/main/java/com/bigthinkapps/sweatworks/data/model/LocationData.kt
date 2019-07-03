package com.bigthinkapps.sweatworks.data.model

import android.os.Parcel
import android.os.Parcelable
import com.bigthinkapps.sweatworks.utils.AppConstants

data class LocationData(
    val street: String = AppConstants.EMPTY_STRING,
    val city: String = AppConstants.EMPTY_STRING,
    val state: String = AppConstants.EMPTY_STRING,
    val postCode: Int = AppConstants.INT_ZERO,
    val coordinates: CoordinatesData = CoordinatesData()
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString() ?: AppConstants.EMPTY_STRING,
        parcel.readString() ?: AppConstants.EMPTY_STRING,
        parcel.readString() ?: AppConstants.EMPTY_STRING,
        parcel.readInt(),
        parcel.readParcelable(CoordinatesData::class.java.classLoader)
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(street)
        parcel.writeString(city)
        parcel.writeString(state)
        parcel.writeInt(postCode)
        parcel.writeParcelable(coordinates, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<LocationData> {
        override fun createFromParcel(parcel: Parcel): LocationData {
            return LocationData(parcel)
        }

        override fun newArray(size: Int): Array<LocationData?> {
            return arrayOfNulls(size)
        }
    }
}