package com.frost.leap.architecture.onboarding.data.model

import android.os.Parcel
import android.os.Parcelable

class UserModel(

    private var studentId: String,
    private var email: String,
    private var countryCode: String,
    private var mobile: String,
    private var firstName: String,
    private var lastName: String,
    private var dateOfBirth: String,
    private var gender: String,
    private var image: String,
    private var fatherName: String,
    private var state: String,
    private var emailVerified: Boolean,
    private var mobileVerified: Boolean,
    private var verificationStatus: String,
    private var verificationCount: Int,
    private var comments: String,
    private var studentComments: String,
    private var manualVerificationStatus: String,
    private var blockScreen: Boolean,

//    private var uploadedDocuments: List<DocumentModel>,
//    private var additionalDetails: AdditionalDetails,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readByte() != 0.toByte(),
        parcel.readByte() != 0.toByte(),
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readByte() != 0.toByte()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(studentId)
        parcel.writeString(email)
        parcel.writeString(countryCode)
        parcel.writeString(mobile)
        parcel.writeString(firstName)
        parcel.writeString(lastName)
        parcel.writeString(dateOfBirth)
        parcel.writeString(gender)
        parcel.writeString(image)
        parcel.writeString(fatherName)
        parcel.writeString(state)
        parcel.writeByte(if (emailVerified) 1 else 0)
        parcel.writeByte(if (mobileVerified) 1 else 0)
        parcel.writeString(verificationStatus)
        parcel.writeInt(verificationCount)
        parcel.writeString(comments)
        parcel.writeString(studentComments)
        parcel.writeString(manualVerificationStatus)
        parcel.writeByte(if (blockScreen) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UserModel> {
        override fun createFromParcel(parcel: Parcel): UserModel {
            return UserModel(parcel)
        }

        override fun newArray(size: Int): Array<UserModel?> {
            return arrayOfNulls(size)
        }
    }

}