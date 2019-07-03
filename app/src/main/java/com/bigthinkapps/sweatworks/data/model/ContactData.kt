package com.bigthinkapps.sweatworks.data.model

import android.os.Parcel
import android.os.Parcelable
import com.bigthinkapps.sweatworks.utils.AppConstants

data class ContactData(
    val gender: String = AppConstants.EMPTY_STRING,
    val name: NameData = NameData(),
    val location: LocationData = LocationData(),
    val email: String = AppConstants.EMPTY_STRING,
    val phone: String = AppConstants.EMPTY_STRING,
    val cell: String = AppConstants.EMPTY_STRING,
    val login: LoginData = LoginData(),
    val picture: PictureData = PictureData(),
    val nat: String = AppConstants.EMPTY_STRING,
    val id: IdData = IdData(),
    val timezone: TimezoneData = TimezoneData(),
    val dob: DobData = DobData(),
    val registered: DobData = DobData()
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: AppConstants.EMPTY_STRING,
        parcel.readParcelable(NameData::class.java.classLoader),
        parcel.readParcelable(LocationData::class.java.classLoader),
        parcel.readString() ?: AppConstants.EMPTY_STRING,
        parcel.readString() ?: AppConstants.EMPTY_STRING,
        parcel.readString() ?: AppConstants.EMPTY_STRING,
        parcel.readParcelable(LoginData::class.java.classLoader),
        parcel.readParcelable(PictureData::class.java.classLoader),
        parcel.readString() ?: AppConstants.EMPTY_STRING,
        parcel.readParcelable(IdData::class.java.classLoader),
        parcel.readParcelable(TimezoneData::class.java.classLoader),
        parcel.readParcelable(DobData::class.java.classLoader),
        parcel.readParcelable(DobData::class.java.classLoader)
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(gender)
        parcel.writeParcelable(name, flags)
        parcel.writeParcelable(location, flags)
        parcel.writeString(email)
        parcel.writeString(phone)
        parcel.writeString(cell)
        parcel.writeParcelable(login, flags)
        parcel.writeParcelable(picture, flags)
        parcel.writeString(nat)
        parcel.writeParcelable(id, flags)
        parcel.writeParcelable(timezone, flags)
        parcel.writeParcelable(dob, flags)
        parcel.writeParcelable(registered, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ContactData> {
        override fun createFromParcel(parcel: Parcel): ContactData {
            return ContactData(parcel)
        }

        override fun newArray(size: Int): Array<ContactData?> {
            return arrayOfNulls(size)
        }
    }
}