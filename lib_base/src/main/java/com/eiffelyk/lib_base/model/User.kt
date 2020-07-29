package com.eiffelyk.lib_base.model

import android.os.Parcel
import android.os.Parcelable
import androidx.databinding.BaseObservable

data class User(
    val id: Int = 0,
    val username: String,
    val icon: String = "",
    val email: String = "",
    val password: String,
    val admin: Boolean,
    val chapterTops: List<Any>,
    val coinCount: Int,
    val collectIds: List<Int>,
    val nickname: String,
    val publicName: String,
    val token: String,
    val type: Int
) : Parcelable, BaseObservable() {
    constructor(source: Parcel) : this(
    source.readInt(),
    source.readString()!!,
    source.readString()!!,
    source.readString()!!,
    source.readString()!!,
    1 == source.readInt(),
    ArrayList<Any>().apply { source.readList(this, Any::class.java.classLoader) },
    source.readInt(),
    ArrayList<Int>().apply { source.readList(this, Int::class.java.classLoader) },
    source.readString()!!,
    source.readString()!!,
    source.readString()!!,
    source.readInt()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt(id)
        writeString(username)
        writeString(icon)
        writeString(email)
        writeString(password)
        writeInt((if (admin) 1 else 0))
        writeList(chapterTops)
        writeInt(coinCount)
        writeList(collectIds)
        writeString(nickname)
        writeString(publicName)
        writeString(token)
        writeInt(type)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<User> = object : Parcelable.Creator<User> {
            override fun createFromParcel(source: Parcel): User = User(source)
            override fun newArray(size: Int): Array<User?> = arrayOfNulls(size)
        }
    }
}