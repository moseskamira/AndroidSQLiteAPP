package com.example.myapplication

import android.os.Parcel
import android.os.Parcelable

class Player(val playerId: Int, val playerName: String?, val playerPosition: String?, val playerImage: Int) :Parcelable
{
    constructor(parcel: Parcel) : this(parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readInt())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(playerId)
        parcel.writeString(playerName)
        parcel.writeString(playerPosition)
        parcel.writeValue(playerImage)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<Player> {
        override fun createFromParcel(parcel: Parcel): Player {
            return Player(parcel)
        }

        override fun newArray(size: Int): Array<Player?> {
            return arrayOfNulls(size)
        }
    }
}