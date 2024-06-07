package com.frost.leap.model

import android.os.Parcel
import android.os.Parcelable


open class BenefitModel(
    var name: String,
    var image: Int,
    var description: String,
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readString()!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeInt(image)
        parcel.writeString(description)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BenefitModel> {
        override fun createFromParcel(parcel: Parcel): BenefitModel {
            return BenefitModel(parcel)
        }

        override fun newArray(size: Int): Array<BenefitModel?> {
            return arrayOfNulls(size)
        }
    }
}
